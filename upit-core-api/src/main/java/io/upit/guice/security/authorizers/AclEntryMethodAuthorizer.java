package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.models.AuthSession;
import io.upit.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import io.upit.security.RequestSession;
import io.upit.services.AclEntryService;
import io.upit.services.AuthSessionService;
import org.aopalliance.intercept.MethodInvocation;

public class AclEntryMethodAuthorizer implements MethodAuthorizer {

    private final AclEntryService aclEntryService;
    private final AuthSessionService authSessionService;
    private final Provider<RequestSession> requestSessionProvider;

    @Inject
    public AclEntryMethodAuthorizer(AclEntryService aclEntryService, AuthSessionService authSessionService, Provider<RequestSession> requestSessionProvider) {
        this.aclEntryService = aclEntryService;
        this.authSessionService = authSessionService;
        this.requestSessionProvider = requestSessionProvider;
    }

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {

    }

}
