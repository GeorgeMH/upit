package io.upit.resteasy.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.ServletModule;
import io.upit.dal.jpa.guice.UpitJpaModule;

public class UpitGuiceModule extends ServletModule {

    @Override
    public void configureServlets() {
        install(new UpitJpaModule());
        filter("/api/*").through(GuiceFilter.class);
        filter("/api/*").through(PersistFilter.class);

        install(new UpitCoreGuiceModule());
        install(new ResteasyModule());
        //install(new UpitJaxRSModule());
    }

}
