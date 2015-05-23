package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import io.upit.security.RequestSession;
import io.upit.services.AclEntryService;
import org.aopalliance.intercept.MethodInvocation;

public class AclEntryMethodAuthorizer implements MethodAuthorizer {

    private final AclEntryService aclEntryService;
    private final Provider<RequestSession> requestSessionProvider;

    @Inject
    public AclEntryMethodAuthorizer(AclEntryService aclEntryService, Provider<RequestSession> requestSessionProvider) {
        this.aclEntryService = aclEntryService;
        this.requestSessionProvider = requestSessionProvider;
    }

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {
        // TODO:
    }
}
