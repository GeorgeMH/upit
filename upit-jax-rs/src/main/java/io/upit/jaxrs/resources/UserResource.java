package io.upit.jaxrs.resources;

import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	private final UserDAO userManager;

	@Inject
	public UserResource(UserDAO userManager) {
		this.userManager = userManager;
	}

	@POST
	public User create(User user) {

		return null;
	}
	
	@PUT
	public void update(User user) {

	}

	@GET
	@Path("{id}/")
	public User getUserById(@PathParam("id") String idStr) {
		return null;
	}


}
