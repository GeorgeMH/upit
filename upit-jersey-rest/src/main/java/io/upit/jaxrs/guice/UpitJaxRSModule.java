package io.upit.jaxrs.guice;

import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import io.upit.jaxrs.guice.providers.JacksonJsonProviderProvider;
import io.upit.jaxrs.guice.providers.ObjectMapperProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UpitJaxRSModule extends ServletModule {
    private final Logger logger = LoggerFactory.getLogger(UpitJaxRSModule.class);

    @Override
    protected void configureServlets() {
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class).in(Singleton.class);
        bind(JacksonJsonProvider.class).toProvider(JacksonJsonProviderProvider.class).in(Singleton.class);

        // Configure the primary REST handlers
        Map<String, String> jerseyInitParams = new HashMap<>();
        jerseyInitParams.put("com.sun.jersey.config.property.packages", "io.upit.jaxrs.resources");
        jerseyInitParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");


        filter("/d/*", "/api_v1/*").through(PersistFilter.class);
        serve("/d/*", "/api_v1/*").with(GuiceContainer.class, jerseyInitParams);
    }

}
