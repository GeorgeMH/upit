package io.upit.web.guice;

import io.upit.core.api.models.AuthSession;
import io.upit.core.api.models.LoginRequest;
import io.upit.core.api.models.User;
import io.upit.core.jpa.api.dal.models.JpaAuthSession;
import io.upit.core.jpa.api.dal.models.JpaLoginRequest;
import io.upit.core.jpa.api.dal.models.JpaUser;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.google.inject.Provider;

public class ObjectMapperProvider implements Provider<ObjectMapper> {

	@Override
	public ObjectMapper get() {

		// TODO: Load the version information dynamically via .properties file
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule abstractTypeModule = new SimpleModule("upit-api-mappings", new Version(0, 0, 1, "SNAPSHOT"));
		abstractTypeModule.addAbstractTypeMapping(AuthSession.class, JpaAuthSession.class);
		abstractTypeModule.addAbstractTypeMapping(User.class, JpaUser.class);
		abstractTypeModule.addAbstractTypeMapping(LoginRequest.class, JpaLoginRequest.class);
		objectMapper.registerModule(abstractTypeModule);

		return objectMapper;
	}

}
