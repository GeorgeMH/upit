package io.upit.jaxrs.resources;

import com.google.inject.persist.Transactional;
import Hashidsjava.Hashids;
import io.upit.UpitServiceException;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import io.upit.jaxrs.exceptions.ResourceException;
import io.upit.services.UserService;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource<User, Long> {

    private final UserService userService;
    private final Hashids hashids;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;

    @Inject
    public UserResource(UserService userService, Hashids hashids, AuthenticationMetaDataDAO authenticationMetaDataDAO) {
        super(User.class, userService);
        this.userService = userService;
        this.hashids = hashids;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
    }

    @GET
    @Path("/hash/{idHash}")
    public User getByIdHash(@PathParam("idHash") String hash) {
        return userService.getByIdHash(hash);
    }

}
