package io.upit.web.dal.guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class UpitDalModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("upit-dal-hibernate"));
	}


}
