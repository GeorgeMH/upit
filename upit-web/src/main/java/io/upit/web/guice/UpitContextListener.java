package io.upit.web.guice;

import io.upit.web.dal.guice.UpitDalModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class UpitContextListener extends GuiceServletContextListener {

	private Injector injector;

	@Override
	protected Injector getInjector() {
		if (null == injector) {
			injector = Guice.createInjector(new UpitDalModule(), new UpitWebModule());
		}
		return injector;
	}

}
