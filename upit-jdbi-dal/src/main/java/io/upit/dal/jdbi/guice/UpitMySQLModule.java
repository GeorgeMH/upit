package io.upit.dal.jdbi.guice;

import javax.inject.Provider;
import javax.sql.DataSource;

import com.google.inject.*;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.PasteDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.jdbi.AuthSessionDAOImpl;
import io.upit.dal.jdbi.PasteDAOImpl;
import io.upit.dal.jdbi.UserDAOImpl;
import org.skife.jdbi.v2.IDBI;

import com.jolbox.bonecp.BoneCPDataSource;

public class UpitMySQLModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IDBI.class).toProvider(DBIProvider.class).in(Singleton.class);

        bind(new TypeLiteral<Provider<? extends AuthSessionDAO>>(){}).to(new TypeLiteral<DAOProvider<AuthSessionDAOImpl>>(){}).in(Singleton.class);

        bind(PasteDAO.class).toProvider(new TypeLiteral<DAOProvider<PasteDAOImpl>>(){}).in(Singleton.class);
        bind(UserDAO.class).toProvider(new TypeLiteral<DAOProvider<UserDAOImpl>>(){}).in(Singleton.class);



        //bind(AuthSessionDAO.class).toProvider(new TypeLiteral<DBIProvider<AuthSessionDAO>>(){});
        //flywayMigration(dataSource);
        //bindDAOs(dataSource);
    }

//    private void flywayMigration(DataSource dataSource) {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(dataSource);
//        flyway.migrate();
//    }
//
//    private void bindDAOs(DataSource dataSource) {
//        IDBI dbi = getUpitDBI(dataSource);
//        bind(IDBI.class).toInstance(dbi);
//
//        bind(AuthSessionDAO.class).toProvider(new DAOProvider<AuthSessionDAO>(dbi, AuthSessionDAOImpl.class));
//        bind(PasteDAO.class).toProvider(new DAOProvider<PasteDAO>(dbi,PasteDAOImpl.class));
//        bind(UserDAO.class).toProvider(new DAOProvider<UserDAO>(dbi, UserDAOImpl.class));
//    }

    @Provides
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

}
