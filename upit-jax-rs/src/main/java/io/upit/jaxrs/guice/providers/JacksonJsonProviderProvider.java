package io.upit.jaxrs.guice.providers;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class JacksonJsonProviderProvider implements Provider<JacksonJsonProvider> {

	private final ObjectMapper jsonMapper;

	@Inject
	public JacksonJsonProviderProvider(ObjectMapper jsonMapper) {
		this.jsonMapper = jsonMapper;
	}

	@Override
	public JacksonJsonProvider get() {
		return new JacksonJsonProvider(jsonMapper);
	}

}
