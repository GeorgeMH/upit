package io.upit.jaxrs.guice;

import io.upit.jaxrs.guice.providers.JacksonJsonProviderProvider;
import io.upit.jaxrs.guice.providers.ObjectMapperProvider;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class UpitWebModule extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/api/*").through(PersistFilter.class);

		//Setup our object mappings for Interface -> Concrete implementation mappings
		// It's to bad we can't let the guice module in upit-core-jpa define these mappings alone,
		// but upit-core-jpa has no context (and should not have context) for jersey/jackson.
		bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class).in(Singleton.class);
		bind(JacksonJsonProvider.class).toProvider(JacksonJsonProviderProvider.class).in(Singleton.class);

		Map<String, String> guiceInitParams = new HashMap<String, String>();
		guiceInitParams.put("com.sun.jersey.config.property.packages", "io.upit.jaxrs.resources");
		// See:http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
		guiceInitParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

		serve("/api/*").with(GuiceContainer.class, guiceInitParams);

	}

}