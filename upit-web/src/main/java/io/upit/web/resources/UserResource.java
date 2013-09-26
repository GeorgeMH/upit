package io.upit.web.resources;

import io.upit.web.dal.mongo.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.inject.Inject;

@Path("/user")
public class UserResource {

	private final UserDAO userDao;

	@Inject
	public UserResource(UserDAO userDao) {
		this.userDao = userDao;
	}

	@GET
	@Produces("text/html")
	public String getHelloWorld() {
		return "Hello World2!";
	}

}
