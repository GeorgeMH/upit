package io.upit.core.guice;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.api.UserManager;
import io.upit.core.api.impls.AuthSessionManagerImpl;
import io.upit.core.api.impls.UserManagerImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;

public class UpitCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("upit-dal-hibernate"));

		bind(AuthSessionManager.class).to(AuthSessionManagerImpl.class).in(Singleton.class);
		bind(UserManager.class).to(UserManagerImpl.class).in(Singleton.class);
	}

}