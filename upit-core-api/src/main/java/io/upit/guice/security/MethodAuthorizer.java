package io.upit.guice.security;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public interface MethodAuthorizer {

    boolean canExecuteMethod(Method method, Object[] arguments, Object invocatingObject, AccessibleObject accessibleObject);

}
