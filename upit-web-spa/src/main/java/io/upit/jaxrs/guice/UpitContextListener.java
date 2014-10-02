package io.upit.jaxrs.guice;

import io.upit.dal.jdbi.guice.UpitMySQLModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class UpitContextListener extends GuiceServletContextListener {

    private Injector injector;
    
    @Override
    protected Injector getInjector() {
        if (null == injector) {
            injector = Guice.createInjector(new UpitMySQLModule(), new UpitJaxRSModule());
        }
        return injector;
    }

}
