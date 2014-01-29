package io.upit.web.resources;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.api.support.LoginRequest;
import io.upit.core.dal.models.AuthSession;

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
public class AuthSessionResource {

	private final AuthSessionManager authSessionManager;

	@Inject
	public AuthSessionResource(AuthSessionManager authSessionManager) {
		this.authSessionManager = authSessionManager;
	}

	@POST
	@Path("login/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public AuthSession login(LoginRequest loginRequest) {
		return authSessionManager.login(loginRequest);
	}

	@POST
	@Path("validate/")
	@Transactional
	public AuthSession validateSession(AuthSession session){
		return authSessionManager.validateSession(session);
	}

	@DELETE
	@Path("delete/")
	public void endSession(AuthSession session) {
		authSessionManager.endSession(session);
	}

}
