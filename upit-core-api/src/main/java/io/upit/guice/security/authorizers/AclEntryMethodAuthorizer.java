package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.AclEntryDAO;
import io.upit.dal.models.AuthSession;
import io.upit.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import io.upit.services.AuthSessionService;
import org.aopalliance.intercept.MethodInvocation;

public class AclEntryMethodAuthorizer implements MethodAuthorizer {

    private final AuthSessionService authSessionServivce;
    private final AclEntryDAO aclEntryDAO;
    private final Provider<AuthSession> authSessionProvider;

    @Inject
    public AclEntryMethodAuthorizer(AuthSessionService authSessionService, AclEntryDAO aclEntryDAO, Provider<AuthSession> authSessionProvider) {
        this.authSessionServivce = authSessionService;
        this.aclEntryDAO = aclEntryDAO;
        this.authSessionProvider = authSessionProvider;
    }

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {
        // TODO:
    }
}
