package io.upit.dal.jdbi.mysql.guice;

import com.google.inject.Provider;
import com.google.inject.Provides;
import io.upit.dal.AbstractDAO;

import java.lang.annotation.Annotation;

public class DAOProviderFactoryProvider implements Provider<DAOProvider<? extends AbstractDAO<?,?>>> {

    @Override
    public DAOProvider<? extends AbstractDAO<?, ?>> get() {
        return null;
    }

}
