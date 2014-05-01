package io.upit.jaxrs.resources;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.api.models.AuthSession;
import io.upit.core.api.models.LoginRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/auth")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource {


	private final AuthSessionManager authSessionManager;

	@Inject
	public AuthSessionResource(AuthSessionManager authSessionManager) {
		this.authSessionManager = authSessionManager;
	}

	@POST
	@Path("login/")
	@Transactional
	public AuthSession login(LoginRequest loginRequest) {
		return authSessionManager.login(loginRequest);
	}

	@POST
	@Path("validate/")
	@Transactional
	public AuthSession validateSession(AuthSession session) {
		return authSessionManager.validateSession(session);
	}

	@DELETE
	@Path("end/")
	@Transactional
	public void endSession(AuthSession session) {
		authSessionManager.endSession(session);
	}

}
