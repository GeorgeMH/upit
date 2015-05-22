package io.upit.jaxrs.resources;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import io.upit.UpitServiceException;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.security.LoginRequest;
import io.upit.dal.models.security.RegistrationRequest;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.DenyAllMethodAuthorizer;
import io.upit.jaxrs.exceptions.ResourceException;
import io.upit.services.AuthSessionService;
import io.upit.services.UserService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/authSession")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource extends AbstractResource<AuthSession, String> {

    private final AuthSessionService authSessionService;
    private final UserService userService;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;
    private final ObjectMapper objectMapper;

    @Inject
    public AuthSessionResource(AuthSessionService authSessionService, UserService userDao, AuthenticationMetaDataDAO authenticationMetaDataDAO, ObjectMapper objectMapper) {
        super(AuthSession.class, authSessionService);
        this.authSessionService = authSessionService;
        this.userService = userDao;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
        this.objectMapper = objectMapper;
    }

    @POST
    @Transactional
    @Path("register/")
    public Response register(RegistrationRequest registrationRequest) {
        try {
            AuthSession ret = authSessionService.register(registrationRequest);
            String jsonAuthSession = objectMapper.writeValueAsString(ret);
            //TODO: Cookie expiration etc
            return Response.ok().cookie(new NewCookie("UPIT_LOGIN_TOKEN", jsonAuthSession)).build();
        } catch (UpitServiceException | IOException e) {
            throw new ResourceException("Failed Registration Request", e);
        }
    }

    @POST
    @Path("login/")
    @Transactional
    @PreAuthorize(methodAuthorizers = {DenyAllMethodAuthorizer.class})
    public AuthSession login(LoginRequest loginRequest) {
        try {
            return authSessionService.login(loginRequest);
        } catch (UpitServiceException e) {
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
