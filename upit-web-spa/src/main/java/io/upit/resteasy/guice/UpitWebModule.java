package io.upit.resteasy.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.upit.dal.jpa.guice.UpitJpaModule;

public class UpitWebModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.install(new UpitCoreGuiceModule());
        binder.install(new UpitJpaModule());
        binder.install(new UpitResteasyModule());
    }
}
