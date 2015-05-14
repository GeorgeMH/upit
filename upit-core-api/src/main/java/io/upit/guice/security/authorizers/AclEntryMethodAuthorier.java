package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import io.upit.dal.AclEntryDAO;
import io.upit.guice.security.MethodAuthorizer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class AclEntryMethodAuthorier implements MethodAuthorizer {

    private final AclEntryDAO aclEntryDAO;

    @Inject
    public AclEntryMethodAuthorier(AclEntryDAO aclEntryDAO) {
        this.aclEntryDAO = aclEntryDAO;
    }

    @Override
    public boolean canExecuteMethod(Method method, Object[] arguments, Object invocatingObject, AccessibleObject accessibleObject) {
        return false;
    }
}
