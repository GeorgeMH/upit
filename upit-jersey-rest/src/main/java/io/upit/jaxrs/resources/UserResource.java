package io.upit.jaxrs.resources;

import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource<User, String>{

    private final UserDAO userDao;

    @Inject
    public UserResource(UserDAO userDao) {
        super(User.class, userDao);
        this.userDao = userDao;
    }

}
