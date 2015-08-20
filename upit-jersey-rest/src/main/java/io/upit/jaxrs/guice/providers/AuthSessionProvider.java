package io.upit.jaxrs.guice.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.servlet.RequestScoped;
import io.upit.UpitServiceException;
import io.upit.dal.models.AuthSession;
import io.upit.jaxrs.guice.RequestSessionFilter;
import io.upit.security.AuthenticationException;
import io.upit.security.UpitSecurityException;
import io.upit.services.AuthSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequestScoped
public class AuthSessionProvider implements Provider<AuthSession> {
    private final static Logger logger = LoggerFactory.getLogger(AuthSessionProvider.class);

    private final AuthSessionService authSessionService;
    private final Provider<String> authSessionIdProvider;


    @Inject
    public AuthSessionProvider(AuthSessionService authSessionService, @Named(RequestSessionFilter.AUTH_SESSION_ID_COOKIE_NAME) Provider<String> authSessionIdProvider) {
        this.authSessionService = authSessionService;
        this.authSessionIdProvider = authSessionIdProvider;
    }

    @Override
    public AuthSession get() {
        AuthSession realAuthSession = null;

        String sessionId = authSessionIdProvider.get();
        if(null != sessionId) {
            try {
                realAuthSession = authSessionService.validateSessionById(sessionId);
            }catch(AuthenticationException e) {
                logger.error("Invalid Auth Session Id for Request Scope: " + sessionId);
            }

            if (null != realAuthSession) {
                return realAuthSession;
            }
        }

        if(null == realAuthSession) {
            try {
                realAuthSession = authSessionService.createAnonymousAuthSession();
            } catch(UpitServiceException e) {
                throw new UpitSecurityException("Failed creating anonymous session", e);
            }
        }
        return realAuthSession;
    }

}
