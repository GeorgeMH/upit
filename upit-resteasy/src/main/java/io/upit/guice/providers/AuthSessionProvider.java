package io.upit.guice.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.UpitServiceException;
import io.upit.dal.models.AuthSession;
import io.upit.security.AuthenticationException;
import io.upit.security.UpitSecurityException;
import io.upit.services.AuthSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Cookie;

public class AuthSessionProvider implements Provider<AuthSession> {
    private final static Logger logger = LoggerFactory.getLogger(AuthSessionProvider.class);
    public static final String AUTH_SESSION_ID_COOKIE_NAME = "AuthSessionId";

    private final AuthSessionService authSessionService;
//    private final HttpRequest httpRequest;

    @Inject
    public AuthSessionProvider(AuthSessionService authSessionService) {
        this.authSessionService = authSessionService;
//        this.httpRequest = httpRequest;
    }

    @Override
    public AuthSession get() {
        AuthSession realAuthSession = null;

        Cookie authCookie = null; //httpRequest.getHttpHeaders().getCookies().get(AUTH_SESSION_ID_COOKIE_NAME);
        if(null != authCookie) {
            String sessionId = authCookie.getValue();
            // TODO: Enforce/validate this by using a JWT?
            if(null != sessionId && sessionId.length() > 0) {
                try {
                    realAuthSession = authSessionService.validateSessionById(sessionId);
                } catch (AuthenticationException e) {
                    logger.error("Invalid Auth Session Id for Request Scope: " + sessionId);
                }
            }

            if (null != realAuthSession) {
                return realAuthSession;
            }
        }

        try {
            realAuthSession = authSessionService.createAnonymousAuthSession();
        } catch(UpitServiceException e) {
            throw new UpitSecurityException("Failed creating anonymous session", e);
        }
        return realAuthSession;
    }

}
