package io.upit.resteasy.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.models.AuthSession;
import io.upit.resteasy.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import io.upit.services.AclEntryService;
import io.upit.services.AuthSessionService;
import org.aopalliance.intercept.MethodInvocation;

public class AclEntryMethodAuthorizer implements MethodAuthorizer {

    private final AclEntryService aclEntryService;
    private final AuthSessionService authSessionService;
    private final Provider<AuthSession> authSessionProvider;

    @Inject
    public AclEntryMethodAuthorizer(AclEntryService aclEntryService, AuthSessionService authSessionService, Provider<AuthSession> authSessionProvider) {
        this.aclEntryService = aclEntryService;
        this.authSessionService = authSessionService;
        this.authSessionProvider = authSessionProvider;
    }

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {

    }

}
