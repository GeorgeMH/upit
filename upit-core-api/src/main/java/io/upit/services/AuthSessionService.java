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
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Calendar;
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

    public AuthSession register(RegistrationRequest registrationRequest) throws UpitServiceException {
        User userToCreate = registrationRequest.getRequestedUser();

        AuthenticationMetaData authenticationMetaData = new AuthenticationMetaDataImpl();
        authenticationMetaData.setPassword(registrationRequest.getPassword());
        authenticationMetaData.setSalt(UUID.randomUUID().toString());
        authenticationMetaData.setAuthenticationProviderURI("sha512");

        User createdUser = userService.create(userToCreate);

        authenticationMetaData.setUserId(createdUser.getId());
        authenticationMetaDataDAO.create(authenticationMetaData);
    }

    public AuthSession login(LoginRequest loginRequest) throws UpitServiceException {
        User user = userService.getByUserNameOrEmail(loginRequest.getUserNameOrEmail());
        if (null == user) {
            throw new UpitServiceException("Invalid username or password");
        }

        // TODO: authenticate? The authentication meta data to use should probalby be specified in the AuthenticationRequest object passed to this method
//        List<AuthenticationMetaData> authenticationMetaDatas = authenticationMetaDataDAO.getByUserId(user.getId());
//
//        AuthenticationMetaData authenticationMetaData = authenticationMetaDatas.stream()
//                .filter( amd -> {
//                    boolean isProvider DEFAULT_AUTHENTICATION_PROVIDER.equals(amd.getAuthenticationProviderURI()); return provider;
//                })
//                .findFirst().get();




        Calendar currentCalendar = Calendar.getInstance();

        AuthSession authSession = new AuthSessionImpl();
        authSession.setId(UUID.randomUUID().toString());
        authSession.setUserId(user.getId());
        authSession.setCreated(currentCalendar.getTime());
        authSession.setActive(true);

        //TODO: Make Expire Configurable
        currentCalendar.add(Calendar.YEAR, 1);
        authSession.setExpires(currentCalendar.getTime());

        authSessionDao.create(authSession);

        return authSession;
    }

    public void endSession(AuthSession authSession) {

    }

}
