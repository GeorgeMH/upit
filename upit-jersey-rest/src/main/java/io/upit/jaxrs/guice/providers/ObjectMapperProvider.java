package io.upit.jaxrs.guice.providers;

import io.upit.dal.models.AuthSession;
import io.upit.dal.models.Paste;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AuthSessionImpl;
import io.upit.dal.models.pojos.PasteImpl;
import io.upit.dal.models.pojos.UserImpl;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.google.inject.Provider;

public class ObjectMapperProvider implements Provider<ObjectMapper> {

    @Override
    public ObjectMapper get() {

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule abstractTypeModule = new SimpleModule("upit-api-mappings", new Version(0, 0, 1, "SNAPSHOT"));
        abstractTypeModule.addAbstractTypeMapping(AuthSession.class, AuthSessionImpl.class);
        abstractTypeModule.addAbstractTypeMapping(User.class, UserImpl.class);
        abstractTypeModule.addAbstractTypeMapping(Paste.class, PasteImpl.class);

        objectMapper.registerModule(abstractTypeModule);

        return objectMapper;
    }

}
