package io.upit.jaxrs.resources;

import io.upit.dal.AuthSessionDAO;
import io.upit.dal.models.AuthSession;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/auth")
@Produces("application/json")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource {

	@Inject
	public AuthSessionResource(AuthSessionDAO authSessionDAO) {
	}

	@POST
	@Path("login/")
	public AuthSession login(@QueryParam("userName") String userName, @QueryParam("password") String password) {
		return null;
	}

	@POST
	@Path("validate/")
	public AuthSession validate(AuthSession session) {
		return null;
	}

	@DELETE
	@Path("end/")
	public void end(AuthSession session) {
	}

}
