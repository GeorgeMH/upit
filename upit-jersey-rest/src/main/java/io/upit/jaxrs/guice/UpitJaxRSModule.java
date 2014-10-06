package io.upit.jaxrs.guice;

import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistFilter;
import io.upit.jaxrs.guice.providers.JacksonJsonProviderProvider;
import io.upit.jaxrs.guice.providers.ObjectMapperProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpitJaxRSModule extends ServletModule {
    private final Logger logger = LoggerFactory.getLogger(UpitJaxRSModule.class);

    @Override
    protected void configureServlets() {

        File uploadedFileRepositoryPath = new File("upitUploadedFiles/");
        if(uploadedFileRepositoryPath.exists()) {
            if(!uploadedFileRepositoryPath.isDirectory()) {
                addError("Upload File Repository exists and is not a directory: " + uploadedFileRepositoryPath.getAbsolutePath());
                return;
            } else if(!uploadedFileRepositoryPath.canWrite()) {
                addError("Upload File Repository exists but is not writable: " + uploadedFileRepositoryPath.getAbsolutePath());
                return;
            }
        } else {
            if(!uploadedFileRepositoryPath.mkdirs()){
                addError("Failed creating Upload File Repository: " + uploadedFileRepositoryPath.getAbsolutePath());
                return;
            }
        }

        logger.info("Uploaded File Repo: " + uploadedFileRepositoryPath.getAbsolutePath());
        System.out.println("Uploaded File Repo: " + uploadedFileRepositoryPath.getAbsolutePath());
        bind(File.class).annotatedWith(Names.named("uploadedFileRepositoryPath")).toInstance(uploadedFileRepositoryPath);

        // Setup our object mappings for Interface -> Concrete implementation mappings
        // It's too bad we can't let the guice module in upit-jpa-impl define these mappings alone,
        // but upit-jpa-impl has no context (and should not) for jersey.
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class).in(Singleton.class);
        bind(JacksonJsonProvider.class).toProvider(JacksonJsonProviderProvider.class).in(Singleton.class);

        Map<String, String> guiceInitParams = new HashMap<String, String>();
        guiceInitParams.put("com.sun.jersey.config.property.packages", "io.upit.jaxrs.resources");
        // See:http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
        guiceInitParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

        filter("/api_v1/*").through(PersistFilter.class);
        serve("/api_v1/*").with(GuiceContainer.class, guiceInitParams);
    }

}
