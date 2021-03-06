package io.upit.guice.security.authorizers;

import io.upit.guice.security.MethodAuthorizer;
import io.upit.security.AuthorizationException;
import org.aopalliance.intercept.MethodInvocation;


public class DenyAllMethodAuthorizer implements MethodAuthorizer {

    @Override
    public void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException {
        throw new AuthorizationException("Deny All");
    }

}
