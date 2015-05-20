package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.models.AuthSession;

import io.upit.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import io.upit.services.AuthSessionService;
import org.aopalliance.intercept.MethodInvocation;

public class AnonymousUserAuthorizer implements MethodAuthorizer {

    private final AuthSessionService authSessionService;
    private final Provider<AuthSession> authSessionProvider;

    @Inject
    public AnonymousUserAuthorizer(AuthSessionService authSessionService, Provider<AuthSession> authSessionProvider) {
        this.authSessionService = authSessionService;
        this.authSessionProvider = authSessionProvider;
    }

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {
        AuthSession currentSession = authSessionProvider.get();

        if(!currentSession.isAnonymous()) {
            throw new AuthorizationException("User must be anonymous");
        }
    }
}
