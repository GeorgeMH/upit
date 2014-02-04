package io.upit.web.authentication;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest reqContext) {

		return reqContext;
	}

}
