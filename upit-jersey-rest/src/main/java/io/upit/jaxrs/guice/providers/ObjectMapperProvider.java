package io.upit.jaxrs.guice.providers;

import io.upit.dal.jpa.models.JpaAuthSession;
import io.upit.dal.jpa.models.JpaPaste;
import io.upit.dal.jpa.models.JpaProperty;
import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.jpa.models.security.JpaAuthenticationMetaData;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.Paste;
import io.upit.dal.models.Property;
import io.upit.dal.models.User;

import io.upit.dal.models.pojos.security.LoginRequestImpl;
import io.upit.dal.models.pojos.security.RegistrationRequestImpl;
import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.dal.models.security.LoginRequest;
import io.upit.dal.models.security.RegistrationRequest;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.google.inject.Provider;

public class ObjectMapperProvider implements Provider<ObjectMapper> {

    @Override
    public ObjectMapper get() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule abstractTypeModule = new SimpleModule("upit-api-mappings", new Version(0, 0, 1, "SNAPSHOT"));

        abstractTypeModule.addAbstractTypeMapping(AuthSession.class, JpaAuthSession.class);
        abstractTypeModule.addAbstractTypeMapping(User.class, JpaUser.class);
        abstractTypeModule.addAbstractTypeMapping(Paste.class, JpaPaste.class);
        abstractTypeModule.addAbstractTypeMapping(Property.class, JpaProperty.class);
        abstractTypeModule.addAbstractTypeMapping(AuthenticationMetaData.class, JpaAuthenticationMetaData.class);
        abstractTypeModule.addAbstractTypeMapping(RegistrationRequest.class, RegistrationRequestImpl.class);
        abstractTypeModule.addAbstractTypeMapping(LoginRequest.class, LoginRequestImpl.class);


        objectMapper.registerModule(abstractTypeModule);

        return objectMapper;
    }

}
