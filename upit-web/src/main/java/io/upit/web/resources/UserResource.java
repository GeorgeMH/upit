package io.upit.web.resources;

import io.upit.web.dal.UserDAO;
import io.upit.web.dal.models.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/user")
@Produces("application/json")
public class UserResource {


	private final UserDAO userDao;

	@Inject
	public UserResource(UserDAO userDao) {
		this.userDao = userDao;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") String idStr) {
		return userDao.getById(Integer.parseInt(idStr));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User create(User user){
		return userDao.create(user);
	}



}
