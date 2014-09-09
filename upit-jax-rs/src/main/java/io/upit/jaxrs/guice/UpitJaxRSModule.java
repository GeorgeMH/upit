package io.upit.jaxrs.guice;

import io.upit.jaxrs.guice.providers.JacksonJsonProviderProvider;
import io.upit.jaxrs.guice.providers.ObjectMapperProvider;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class UpitJaxRSModule extends ServletModule {

    @Override
    protected void configureServlets() {
        // Setup our object mappings for Interface -> Concrete implementation mappings
        // It's too bad we can't let the guice module in upit-jpa-impl define these mappings alone,
        // but upit-jpa-impl has no context (and should not) for jersey.
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class).in(Singleton.class);
        bind(JacksonJsonProvider.class).toProvider(JacksonJsonProviderProvider.class).in(Singleton.class);

        Map<String, String> guiceInitParams = new HashMap<String, String>();
        guiceInitParams.put("com.sun.jersey.config.property.packages", "io.upit.jaxrs.resources");
        // See:http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
        guiceInitParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

        //filter("/api/*").through(PersistFilter.class);
        serve("/api_v1/*").with(GuiceContainer.class, guiceInitParams);
    }

}
