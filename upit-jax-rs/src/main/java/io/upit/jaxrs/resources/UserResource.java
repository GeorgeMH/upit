package io.upit.jaxrs.resources;

import io.upit.core.api.UserManager;
import io.upit.core.api.models.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Path("/user")
@Produces("application/json")
public class UserResource {

	private final UserManager userManager;

	@Inject
	public UserResource(UserManager userManager) {
		this.userManager = userManager;
	}

	@GET
	@Path("get/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public User getUserById(@PathParam("id") String idStr) {
		return userManager.getUserById(Long.parseLong(idStr));
	}

	@POST
	@Path("register/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public User register(@FormParam("user") User user) {
		return userManager.register(user);
	}

}