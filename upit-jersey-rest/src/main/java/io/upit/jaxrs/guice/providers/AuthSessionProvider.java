package io.upit.jaxrs.guice.providers;

import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.servlet.RequestScoped;
import io.upit.UpitServiceException;
import io.upit.dal.models.AuthSession;
import io.upit.security.UpitSecurityException;
import io.upit.services.AuthSessionService;


@RequestScoped
public class AuthSessionProvider implements Provider<AuthSession> {
    private final AuthSessionService authSessionService;
    private final String sessionId;

    public AuthSessionProvider(AuthSessionService authSessionService, @Named("AuthSessionId") String sessionId) {
        this.authSessionService = authSessionService;
        this.sessionId = sessionId;
    }

    @Override
    public AuthSession get() {
        AuthSession realAuthSession = null;

        if(null != sessionId) {
            realAuthSession = authSessionService.getById(sessionId);
            if (null != realAuthSession) {
                return realAuthSession;
            }
        } else{
            try {
                realAuthSession = authSessionService.createAnonymousAuthSession();
            } catch(UpitServiceException e) {
                throw new UpitSecurityException("Failed creating anonymous session", e);
            }
        }
        return realAuthSession;
    }
}
