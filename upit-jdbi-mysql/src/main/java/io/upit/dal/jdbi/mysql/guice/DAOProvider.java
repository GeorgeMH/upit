package io.upit.dal.jdbi.mysql.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.skife.jdbi.v2.IDBI;

/**
 * Created by george on 9/1/14.
 */
public class DAOProvider<T> implements Provider<T> {

    private final Class<? extends T> daoClass;
    private final IDBI dbi;

    @Inject
    public DAOProvider(IDBI dbi, Class<? extends T> daoClass) {
        this.dbi = dbi;
        this.daoClass = daoClass;
    }

    @Override
    public T get() {
        return dbi.onDemand(daoClass);
    }

}
