package io.upit.resteasy.resources;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import io.upit.UpitServiceException;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.security.LoginRequest;
import io.upit.dal.models.security.RegistrationRequest;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.guice.security.authorizers.AnonymousUserAuthorizer;
import io.upit.resteasy.exceptions.ResourceException;
import io.upit.guice.providers.AuthSessionProvider;
import io.upit.security.AuthenticationException;
import io.upit.services.AuthSessionService;
import io.upit.services.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("authSession/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthSessionResource extends AbstractResource<AuthSession, String> {

    private final AuthSessionService authSessionService;
    private final UserService userService;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;

    @Inject
    public AuthSessionResource(AuthSessionService authSessionService, UserService userService, AuthenticationMetaDataDAO authenticationMetaDataDAO) {
        super(AuthSession.class, authSessionService);
        this.authSessionService = authSessionService;
        this.userService = userService;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
    }

    @POST
    @Transactional
    @Path("anonymous/")
    @PreAuthorize
    public Response getAnonymousSession() {
        try {
            AuthSession ret = authSessionService.createAnonymousAuthSession();
            return Response.ok(ret).cookie(createAuthCookie(ret.getId())).build();
        } catch (UpitServiceException e) {
            throw new ResourceException("Failed Registration Request", e);
        }
    }

    @POST
    @Transactional
    @Path("register/")
    @PreAuthorize
    public Response register(RegistrationRequest registrationRequest) {
        try {
            AuthSession ret = authSessionService.register(registrationRequest);
            return Response.ok(ret).cookie(new NewCookie(AuthSessionProvider.AUTH_SESSION_ID_COOKIE_NAME, ret.getId())).build();
        } catch (UpitServiceException e) {
            throw new ResourceException("Failed Registration Request", e);
        }
    }

    @POST
    @Path("login/")
    @Transactional
    @PreAuthorize(methodAuthorizers = {AnonymousUserAuthorizer.class})
    public Response login(LoginRequest loginRequest) {
        try {
            AuthSession ret = authSessionService.login(loginRequest);
            return Response.ok(ret).cookie(createAuthCookie(ret.getId())).build();
        } catch (UpitServiceException e) {
            throw new ResourceException("Failed logging in", e);
        }
    }

    @GET
    @Path("validate/{sessionId}")
    @Transactional
    @PreAuthorize
    public Response validate(@PathParam("sessionId") String sessionId) throws AuthenticationException {
        AuthSession ret = authSessionService.validateSessionById(sessionId);
        return Response.ok(ret).cookie(createAuthCookie(ret.getId())).build();
    }

    @DELETE
    @Path("end/{sessionId}")
    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public Response end(@PathParam("sessionId") String sessionId) {
        authSessionService.endSession(sessionId);
        return Response.ok().cookie(createAuthCookie(sessionId, 0)).build();
    }

    private NewCookie createAuthCookie(String sessionId) {
        return createAuthCookie(sessionId, 86400); // TODO: make this sane/configurable
    }

    private NewCookie createAuthCookie(String sessionId, int ttl) {
        return new NewCookie(AuthSessionProvider.AUTH_SESSION_ID_COOKIE_NAME, sessionId, "/", null, null, ttl, false);
    }
}
