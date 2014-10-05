package io.upit.dal.jdbi.guice;

import com.google.inject.Provider;
import io.upit.dal.DAO;

public class DAOProviderFactoryProvider implements Provider<DAOProvider<? extends DAO<?,?>>> {

    @Override
    public DAOProvider<? extends DAO<?, ?>> get() {
        return null;
    }

}
