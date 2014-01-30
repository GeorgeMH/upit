package io.upit.core.jpa.guice;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.api.UserManager;
import io.upit.core.api.models.AuthSession;
import io.upit.core.api.models.User;
import io.upit.core.jpa.api.JpaAuthSessionManager;
import io.upit.core.jpa.api.JpaUserManager;
import io.upit.core.jpa.api.dal.models.JpaAuthSession;
import io.upit.core.jpa.api.dal.models.JpaUser;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;

public class UpitCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("upit-dal-hibernate"));

		bind(AuthSessionManager.class).to(JpaAuthSessionManager.class).in(Singleton.class);
		bind(UserManager.class).to(JpaUserManager.class).in(Singleton.class);

		bind(User.class).to(JpaUser.class);
		bind(AuthSession.class).to(JpaAuthSession.class);
	}

}