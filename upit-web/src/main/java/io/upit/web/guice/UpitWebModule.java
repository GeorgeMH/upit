package io.upit.web.guice;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class UpitWebModule extends ServletModule {

	@Override
	protected void configureServlets() {
		Map<String, String> guiceInitParams = new HashMap<String, String>();
		guiceInitParams.put("com.sun.jersey.config.property.packages", "io.upit.web.resources");
		serve("/api/*").with(GuiceContainer.class, guiceInitParams);
	}
	
}
