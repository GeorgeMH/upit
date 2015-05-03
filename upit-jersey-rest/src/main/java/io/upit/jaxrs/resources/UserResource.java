package io.upit.jaxrs.resources;

import com.google.inject.persist.Transactional;
import fm.jiecao.lib.Hashids;
import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource<User, Long> {

    private final UserDAO userDao;
    private final Hashids hashids;

    @Inject
    public UserResource(UserDAO userDao, Hashids hashids) {
        super(User.class, userDao);
        this.userDao = userDao;
        this.hashids = hashids;
    }

    @POST
    @Transactional
    public User create(User user) {
        User createdUser = super.create(user);
        createdUser.setIdHash(hashids.encode(user.getId()));
        return update(createdUser);
    }

    @GET
    @Path("/hash/{idHash}")
    public User getByIdHash(@PathParam("idHash") String hash) {
        return userDao.getByIdHash(hash);
    }

}
