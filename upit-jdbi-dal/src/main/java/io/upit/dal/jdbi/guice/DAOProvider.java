package io.upit.dal.jdbi.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.DAO;
import org.skife.jdbi.v2.IDBI;

public class DAOProvider<TheDAO extends DAO<?, ?>> implements Provider<TheDAO> {

    private final IDBI dbi;
    private final Class<TheDAO> daoClass;

    @Inject
    public DAOProvider(IDBI dbi, Class<TheDAO> daoClass) {
        this.dbi = dbi;
        this.daoClass = daoClass;
    }

    @Override
    public TheDAO get() {
        return dbi.onDemand(daoClass);
    }

}
