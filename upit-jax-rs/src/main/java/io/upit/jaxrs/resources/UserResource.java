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

	private final UserDAO userDao;

	@Inject
	public UserResource(UserDAO userDao) {
		this.userDao = userDao;
	}

	@POST
	public String create(User user) {
		return userDao.create(user);
	}
	
	@PUT
	public void update(User user) {
		userDao.update(user);
	}

	@GET
	@Path("{id}/")
	public User getUserById(@PathParam("id") String idStr) {
		return userDao.getById(idStr);
	}


}
