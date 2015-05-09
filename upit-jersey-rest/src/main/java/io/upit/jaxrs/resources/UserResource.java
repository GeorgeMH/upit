package io.upit.jaxrs.resources;

import com.google.inject.persist.Transactional;
import fm.jiecao.lib.Hashids;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.jaxrs.dto.RegistrationRequest;
import io.upit.jaxrs.exceptions.ResourceException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractResource<User, Long> {

    private final UserDAO userDao;
    private final Hashids hashids;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;

    @Inject
    public UserResource(UserDAO userDao, Hashids hashids, AuthenticationMetaDataDAO authenticationMetaDataDAO) {
        super(User.class, userDao);
        this.userDao = userDao;
        this.hashids = hashids;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
    }

    @POST
    @Transactional
    public User create(User resource) {
        if(null != resource.getId()) {
            throw new ResourceException("Unable to create resource that already has an ID");
        }
        userDao.create(resource);
        resource.setIdHash(hashids.encode(resource.getId()));
        return userDao.update(resource);
    }

    @POST
    @Transactional
    @Path("register/")
    public User register(RegistrationRequest registrationRequest) {
        User userToCreate = registrationRequest.getRequestedUser();

        //TODO: AUTHENTICATE The provided AuthenticationMetaData UNLESS the provider it specifies is not our own local database.
        AuthenticationMetaData authenticationMetaData = registrationRequest.getAuthenticationMetaData();
        if("sha512".equals(authenticationMetaData.getAuthenticationProviderURI())) {
            String passwordSalt = UUID.randomUUID().toString();
            authenticationMetaData.setSalt(passwordSalt);
            authenticationMetaData.setPassword(DigestUtils.sha512Hex(authenticationMetaData.getSalt() + authenticationMetaData.getPassword()));
        } else {
            throw new ResourceException("Attempted to authenticate with unknown Authentication URI");
        }

        User createdUser = create(userToCreate);

        authenticationMetaData.setUserId(createdUser.getId());
        authenticationMetaDataDAO.create(authenticationMetaData);

        // TODO: ACL Generation for these objects

        return createdUser;
    }

    @GET
    @Path("/hash/{idHash}")
    public User getByIdHash(@PathParam("idHash") String hash) {
        return userDao.getByIdHash(hash);
    }

}
