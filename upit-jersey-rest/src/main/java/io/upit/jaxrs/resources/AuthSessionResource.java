package io.upit.jaxrs.resources;

import com.google.inject.persist.Transactional;
import io.upit.UpitServiceException;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.User;

import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.dal.models.pojos.security.RegistrationRequestImpl;
import io.upit.dal.models.security.LoginRequest;
import io.upit.jaxrs.exceptions.ResourceException;
import io.upit.services.AuthSessionService;
import io.upit.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;

@Path("/authSession")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource extends AbstractResource<AuthSession, String> {

    private final AuthSessionService authSessionService;
    private final UserService userService;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;

    @Inject
    public AuthSessionResource(AuthSessionService authSessionService, UserService userDao, AuthenticationMetaDataDAO authenticationMetaDataDAO) {
        super(AuthSession.class, authSessionService);
        this.authSessionService = authSessionService;
        this.userService = userDao;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
    }

    @POST
    @Transactional
    @Path("register/")
    public AuthSession register(RegistrationRequestImpl registrationRequest) {
        try {
            return authSessionService.register(registrationRequest);
        } catch (UpitServiceException e) {
            throw new ResourceException("Failed Registration Request", e);
        }
    }

    @POST
    @Path("login/")
    @Transactional
    public AuthSession login(LoginRequest loginRequest) {
        try {
            return authSessionService.login(loginRequest);
        } catch( UpitServiceException e ) {
            throw new ResourceException("Failed logging in", e);
        }
    }

    @POST
    @Path("validate/${sessionId}")
    public AuthSession validate(@PathParam("sessionId") String sessionId) {
        AuthSession ret = authSessionService.getById(sessionId);
        return null != ret && ret.isActive() ? ret : null;
    }

    @DELETE
    @Path("end/${sessionId}")
    public void end(AuthSession session) {
        authSessionService.endSession(session);
    }

}
