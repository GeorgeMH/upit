package io.upit.dal.jdbi.guice;

import com.google.inject.Provider;
import io.upit.dal.AbstractDAO;

public class DAOProviderFactoryProvider implements Provider<DAOProvider<? extends AbstractDAO<?,?>>> {

    @Override
    public DAOProvider<? extends AbstractDAO<?, ?>> get() {
        return null;
    }

}
