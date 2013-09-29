package io.upit.web.resources;

import io.upit.web.dal.models.User;
import io.upit.web.dal.mongo.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mongodb.morphia.Key;

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
	public User getUserById(@PathParam("id") String id) {
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User create(User user){
		Key<User> key = this.userDao.save(user);
		// TODO: does the user auto get its id set?
		return user;
	}



}
