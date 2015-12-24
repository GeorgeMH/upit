package io.upit.resteasy.guice;

import com.google.inject.AbstractModule;
import io.upit.dal.jpa.guice.UpitJpaModule;

public class UpitGuiceModule extends AbstractModule {

    public void configure() {
        install(new UpitJpaModule());
        install(new UpitCoreGuiceModule());
        install(new ResteasyModule());
        //install(new UpitJaxRSModule());
    }

}
