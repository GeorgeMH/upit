package io.upit.dal.jdbi.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.AbstractDAO;
import org.skife.jdbi.v2.IDBI;

public class DAOProvider<DAO extends AbstractDAO<?, ?>> implements Provider<DAO> {

    private final IDBI dbi;
    private final Class<DAO> daoClass;

    @Inject
    public DAOProvider(IDBI dbi, Class<DAO> daoClass) {
        this.dbi = dbi;
        this.daoClass = daoClass;
    }

    @Override
    public DAO get() {
        return dbi.onDemand(daoClass);
    }

}
