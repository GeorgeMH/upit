package io.upit.web.resources;

import io.upit.core.dal.dao.UserDAO;
import io.upit.core.dal.models.User;

import java.util.Date;

import javax.ws.rs.Consumes;
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

	private final UserDAO userDao;

	@Inject
	public UserResource(UserDAO userDao) {
		this.userDao = userDao;
	}

	@GET
	@Path("get/{id}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public User getUserById(@PathParam("id") String idStr) {
		// TODO: Authentication/Authorization
		return userDao.getById(Integer.parseInt(idStr));
	}

	@POST
	@Path("register/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public User register(User user) {
		// TODO: Input Validation

		//if (null == user.getEmail() || "".equals(user.getEmail().trim())) {
		//	throw new Invalid
		//}

		// Hash the password
		user.setDateCreated(new Date());
		return userDao.create(user);
	}



}
