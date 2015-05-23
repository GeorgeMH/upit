package io.upit.jaxrs.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import io.upit.dal.jpa.guice.UpitJpaModule;
import io.upit.guice.UpitCoreGuiceModule;

public class UpitContextListener extends GuiceServletContextListener {

    private Injector injector;

    @Override
    protected Injector getInjector() {
        if (null == injector) {
            injector = Guice.createInjector(new UpitCoreGuiceModule(), new UpitJpaModule(), new UpitJaxRSModule());
        }
        return injector;
    }

}
