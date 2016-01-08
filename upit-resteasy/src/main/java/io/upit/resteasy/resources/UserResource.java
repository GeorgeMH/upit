package io.upit.resteasy.resources;

import com.google.inject.Inject;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.models.User;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.services.UserService;
import org.hashids.Hashids;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
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
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public User getByIdHash(@PathParam("idHash") String hash) {
        return userService.getByIdHash(hash);
    }

}
