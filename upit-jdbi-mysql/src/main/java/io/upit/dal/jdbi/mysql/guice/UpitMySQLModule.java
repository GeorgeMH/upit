package io.upit.dal.jdbi.mysql.guice;

import javax.sql.DataSource;

import org.skife.jdbi.v2.ClasspathStatementLocator;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.IDBI;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.jolbox.bonecp.BoneCPDataSource;

public class UpitMySQLModule extends AbstractModule {

	@Override
	protected void configure() {

	}

	@Provides
	@Singleton
	public DataSource getUpitDataSource() {
		// TODO: Load these properly
		String dbHost = "";
		String dbUser = "";
		String dbPass = "";
		String dbName = "";

		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setJdbcUrl(String.format("jdbc:mysql://%s/%s", dbHost, dbName));
		dataSource.setUser(dbUser);
		dataSource.setPassword(dbPass);

		dataSource.setDefaultAutoCommit(false);
		dataSource.setDefaultTransactionIsolation("READ_COMMITTED");

		return dataSource;
	}

	@Provides
	@Singleton
	private IDBI getUpitDBI() {
		DBI ret = new DBI(getUpitDataSource());
		ret.setStatementLocator(new ClasspathStatementLocator());
		return ret;
	}

}
