package io.upit.guice.security;

import io.upit.security.AuthorizationException;
import org.aopalliance.intercept.MethodInvocation;


public interface MethodAuthorizer {

    void authorizeMethodInvocation(MethodInvocation methodInvocation) throws AuthorizationException;

}
