package io.upit.web.resources;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.jpa.api.dal.models.JpaAuthSession;
import io.upit.core.jpa.api.support.LoginRequest;

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
	public JpaAuthSession login(LoginRequest loginRequest) {
		return authSessionManager.login(loginRequest);
	}

	@POST
	@Path("validate/")
	@Transactional
	public JpaAuthSession validateSession(JpaAuthSession session){
		return authSessionManager.validateSession(session);
	}

	@DELETE
	@Path("delete/")
	public void endSession(JpaAuthSession session) {
		authSessionManager.endSession(session);
	}

}
