package io.upit.services;

import com.google.inject.Inject;
import io.upit.UpitServiceException;
import io.upit.dal.AuthSessionDAO;
import io.upit.dal.AuthenticationMetaDataDAO;
import io.upit.dal.models.AuthSession;
import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AuthSessionImpl;
import io.upit.dal.models.pojos.security.AuthenticationMetaDataImpl;
import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.dal.models.security.LoginRequest;
import io.upit.dal.models.security.RegistrationRequest;
import io.upit.guice.security.PreAuthorize;
import io.upit.guice.security.authorizers.AclEntryMethodAuthorizer;
import io.upit.guice.security.authorizers.AnonymousUserAuthorizer;
import io.upit.security.AuthenticationException;
import io.upit.security.AuthenticationProvider;
import io.upit.security.providers.Sha512AuthenticationProvider;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AuthSessionService extends AbstractResourceService<AuthSession, String> {

    private final AuthSessionDAO authSessionDao;
    private final UserService userService;
    private final AuthenticationMetaDataDAO authenticationMetaDataDAO;

    private final String DEFAULT_AUTHENTICATION_PROVIDER = "sha512";

    @Inject
    public AuthSessionService(AuthSessionDAO authSessionDAO, AuthenticationMetaDataDAO authenticationMetaDataDAO, UserService userService) {
        super(AuthSession.class, authSessionDAO);
        this.authSessionDao = authSessionDAO;
        this.authenticationMetaDataDAO = authenticationMetaDataDAO;
        this.userService = userService;
    }


    @PreAuthorize(methodAuthorizers = {AnonymousUserAuthorizer.class})
    public AuthSession createAnonymousAuthSession() throws UpitServiceException {
        return createNewAuthSession(null, null);
    }

    @PreAuthorize(methodAuthorizers = {AnonymousUserAuthorizer.class})
    public AuthSession register(RegistrationRequest registrationRequest) throws UpitServiceException {
        User userToCreate = registrationRequest.getRequestedUser();

        // TODO: Multi Authentication support via plugins (OAuth etc)
        AuthenticationMetaData authenticationMetaData = new AuthenticationMetaDataImpl();
        authenticationMetaData.setSalt(UUID.randomUUID().toString());
        authenticationMetaData.setSaltedPassword(Sha512AuthenticationProvider.getSha512Hash(authenticationMetaData.getSalt(), registrationRequest.getPassword()));
        authenticationMetaData.setAuthenticationType("sha512");

        User createdUser = userService.create(userToCreate);

        authenticationMetaData.setUserId(createdUser.getId());
        authenticationMetaDataDAO.create(authenticationMetaData);

        // TODO: Since this requires the current user to be an anonymous session (that would be auto created), should we end the previous session?
        return createNewAuthSession(null, null);
    }

    @PreAuthorize(methodAuthorizers = {AnonymousUserAuthorizer.class})
    public AuthSession login(LoginRequest loginRequest) throws UpitServiceException {
        User user = userService.getByUserNameOrEmail(loginRequest.getUserNameOrEmail());
        if (null == user) {
            throw new AuthenticationException("Invalid username or password");
        }

        // TODO: authenticate? The authentication meta data to use should probalby be specified in the AuthenticationRequest object passed to this method
        List<AuthenticationMetaData> authenticationMetaDatas = authenticationMetaDataDAO.getByUserId(user.getId());

        AuthenticationMetaData authenticationMetaData = authenticationMetaDatas.stream()
            .filter(amd -> loginRequest.getRequestType().equals(amd.getAuthenticationType()))
            .findFirst().get();

        if (null == authenticationMetaData) {
            throw new AuthenticationException("Invalid username or password");
        }

        // Hard coded Sha512 authentication provider for now
        AuthenticationProvider authenticationProvider = new Sha512AuthenticationProvider();
        if (!authenticationProvider.authenticate(loginRequest, authenticationMetaData)) {
            throw new AuthenticationException("Invalid username or password");
        }

        // TODO: LoginRequest should specifiy an expires date for the login session
        return createNewAuthSession(user, null);
    }

    @PreAuthorize
    public AuthSession validateSessionById(String id) throws AuthenticationException {
        AuthSession realAuthSession = authSessionDao.getById(id);

        if (null == realAuthSession) {
            throw new AuthenticationException("Invalid Auth Session");
        }

        if(new Date().getTime() >= realAuthSession.getExpires().getTime()){
            realAuthSession.setActive(false);
            authSessionDao.update(realAuthSession);
            throw new AuthenticationException("Invalid Auth Session");
        }

        if (!realAuthSession.isActive()) {
            throw new AuthenticationException("Invalid Auth Session");
        }

        return realAuthSession;
    }

    @PreAuthorize
    public AuthSession validateSession(AuthSession session) throws AuthenticationException {
        return validateSessionById(session.getId());
    }

    @PreAuthorize(methodAuthorizers = {AclEntryMethodAuthorizer.class})
    public void endSession(AuthSession session) {
        AuthSession realAuthSession = authSessionDao.getById(session.getId());
        realAuthSession.setActive(false);
        authSessionDao.update(realAuthSession);
    }

    private AuthSession createNewAuthSession(User user, Date expires) throws UpitServiceException {
        AuthSession authSession = new AuthSessionImpl();
        authSession.setActive(true);
        authSession.setAnonymous(null == user);
        authSession.setUserId((null == user ? null : user.getId()));
        authSession.setExpires(null);
        authSession.setExpires(expires);
        authSession.setLastValidated(null);
        return authSessionDao.create(authSession);
    }
}
