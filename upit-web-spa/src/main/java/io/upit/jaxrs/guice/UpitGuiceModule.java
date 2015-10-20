package io.upit.jaxrs.guice;

import com.google.inject.AbstractModule;
import io.upit.dal.jpa.guice.UpitJpaModule;
import io.upit.guice.UpitCoreGuiceModule;

public class UpitGuiceModule extends AbstractModule {

    public void configure() {
        install(new UpitCoreGuiceModule());
        install(new UpitJpaModule());
        //install(new UpitJaxRSModule());
        install(new ResteasyModule());
    }

}
