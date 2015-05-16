package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.models.AuthSession;

import io.upit.guice.security.MethodAuthorizer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class AnonymousUserAuthorizer implements MethodAuthorizer {

    private final Provider<AuthSession> authSessionProvider;

    @Inject
    public AnonymousUserAuthorizer(Provider<AuthSession> authSessionProvider) {
        this.authSessionProvider = authSessionProvider;
    }


    @Override
    public boolean canExecuteMethod(Method method, Object[] arguments, Object invocatingObject, AccessibleObject accessibleObject) {

        return false;
    }
}
