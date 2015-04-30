package io.upit.jaxrs.resources;

import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource<User, Long>{

    private final UserDAO userDao;

    @Inject
    public UserResource(UserDAO userDao) {
        super(User.class, userDao);
        this.userDao = userDao;
    }

    @GET
    @Path("/hash/{idHash}")
    public User getByIdHash(@PathParam("idHash") String hash) {
        return userDao.getByIdHash(hash);
    }

}
