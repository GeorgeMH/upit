package io.upit.jaxrs.resources;

import com.google.inject.Provider;
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
public class UserResource extends AbstractResource<User, String>{

    private final Provider<UserDAO> userDao;

    @Inject
    public UserResource(Provider<UserDAO> userDao) {
        super(User.class, userDao);
        this.userDao = userDao;
    }

}
