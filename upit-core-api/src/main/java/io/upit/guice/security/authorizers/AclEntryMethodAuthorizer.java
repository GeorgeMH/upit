package io.upit.guice.security.authorizers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.dal.AclEntryDAO;
import io.upit.dal.models.User;
import io.upit.guice.security.MethodAuthorizer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class AclEntryMethodAuthorizer implements MethodAuthorizer {

    private final AclEntryDAO aclEntryDAO;
    private final Provider<User> userProvider;

    @Inject
    public AclEntryMethodAuthorizer(AclEntryDAO aclEntryDAO, Provider<User> userProvider) {
        this.aclEntryDAO = aclEntryDAO;
        this.userProvider = userProvider;
    }

    @Override
    public boolean canExecuteMethod(Method method, Object[] arguments, Object invocatingObject, AccessibleObject accessibleObject) {
        User user = userProvider.get(); // RequestLevel user


        return false;
    }
}
