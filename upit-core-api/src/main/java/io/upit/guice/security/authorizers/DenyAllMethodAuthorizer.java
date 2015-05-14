package io.upit.guice.security.authorizers;

import io.upit.guice.security.MethodAuthorizer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class DenyAllMethodAuthorizer implements MethodAuthorizer {

    @Override
    public boolean canExecuteMethod(Method method, Object[] arguments, Object invocatingObject, AccessibleObject accessibleObject) {
        return false;
    }

}
