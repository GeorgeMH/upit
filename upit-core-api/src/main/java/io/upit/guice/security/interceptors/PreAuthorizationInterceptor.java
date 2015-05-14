package io.upit.guice.security.interceptors;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.upit.services.AuthSessionService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PreAuthorizationInterceptor implements MethodInterceptor {

    private final Provider<AuthSessionService> authSessionServiceProvider;

    @Inject
    public PreAuthorizationInterceptor(Provider<AuthSessionService> authSessionServiceProvider){
        this.authSessionServiceProvider = authSessionServiceProvider;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // TODO: Lookup the AuthSession token and validate it.
        return invocation.proceed();
    }

}
