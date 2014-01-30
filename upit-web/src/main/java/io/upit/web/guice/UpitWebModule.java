package io.upit.web.guice;

import io.upit.core.jpa.guice.UpitCoreModule;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class UpitWebModule extends ServletModule {

	@Override
	protected void configureServlets() {
		install(new UpitCoreModule());

		filter("/api/*").through(PersistFilter.class);

		Map<String, String> guiceInitParams = new HashMap<String, String>();
		guiceInitParams.put("com.sun.jersey.config.property.packages", "io.upit.web.resources");

		// See: http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
		guiceInitParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

		serve("/api/*").with(GuiceContainer.class, guiceInitParams);
	}
	
}
