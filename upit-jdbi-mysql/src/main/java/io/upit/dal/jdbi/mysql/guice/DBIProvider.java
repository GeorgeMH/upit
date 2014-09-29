package io.upit.dal.jdbi.mysql.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.skife.jdbi.v2.ClasspathStatementLocator;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class DBIProvider implements Provider<IDBI> {

    private final DataSource dataSource;

    @Inject
    public DBIProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public IDBI get() {
        DBI ret = new DBI(dataSource);
        ret.setStatementLocator(new ClasspathStatementLocator());
        ret.registerArgumentFactory(new DateArgumentFactory());
        return ret;
    }

    private static class DateArgumentFactory implements ArgumentFactory<Date> {

        @Override
        public boolean accepts(Class<?> aClass, Object o, StatementContext statementContext) {
            if(null == o) {
                return true;
            }
            return java.util.Date.class.isAssignableFrom(o.getClass());
        }

        @Override
        public Argument build(Class<?> aClass, final Date o, StatementContext statementContext) {
            return new Argument() {
                @Override
                public void apply(int i, PreparedStatement preparedStatement, StatementContext statementContext) throws SQLException {
                    Timestamp timestamp = null == o ? null : new Timestamp(o.getTime());
                    preparedStatement.setTimestamp(i, timestamp);
                }
            };
        }

    }

}

