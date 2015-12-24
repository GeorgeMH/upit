package io.upit.resteasy.guice.security.authorizers;

import io.upit.resteasy.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import org.aopalliance.intercept.MethodInvocation;

public class AllowAllMethodAuthorizer implements MethodAuthorizer {

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {
        // NOOP
    }

}
