package io.upit.dal.jdbi.mysql.guice;

import javax.sql.DataSource;

import io.upit.dal.AuthSessionDAO;
import io.upit.dal.PasteDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.jdbi.mysql.AuthSessionDAOImpl;
import io.upit.dal.jdbi.mysql.PasteDAOImpl;
import io.upit.dal.jdbi.mysql.UserDAOImpl;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.ClasspathStatementLocator;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.IDBI;

import com.google.inject.AbstractModule;
import com.jolbox.bonecp.BoneCPDataSource;

public class UpitMySQLModule extends AbstractModule {

    @Override
    protected void configure() {
        DataSource dataSource = getUpitDataSource();

        flywayMigration(dataSource);
        bindDAOs(dataSource);
    }

    private void flywayMigration(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

    private void bindDAOs(DataSource dataSource) {
        IDBI dbi = getUpitDBI(dataSource);
        bind(IDBI.class).toInstance(dbi);

        bind(AuthSessionDAO.class).toProvider(new DAOProvider<AuthSessionDAO>(dbi, AuthSessionDAOImpl.class));
        bind(PasteDAO.class).toProvider(new DAOProvider<PasteDAO>(dbi,PasteDAOImpl.class));
        bind(UserDAO.class).toProvider(new DAOProvider<UserDAO>(dbi, UserDAOImpl.class));
    }

    private DataSource getUpitDataSource() {
        // TODO: Load these properly
        String dbHost = "10.10.10.100";
        String dbUser = "upit";
        String dbPass = "blackdoor";
        String dbName = "upit";

        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setJdbcUrl(String.format("jdbc:mysql://%s/%s", dbHost, dbName));
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPass);

        dataSource.setDefaultAutoCommit(false);
        dataSource.setDefaultTransactionIsolation("READ_COMMITTED");
        return dataSource;
    }

    private IDBI getUpitDBI(DataSource dataSource) {
        DBI ret = new DBI(dataSource);
        ret.setStatementLocator(new ClasspathStatementLocator());
        return ret;
    }


}
