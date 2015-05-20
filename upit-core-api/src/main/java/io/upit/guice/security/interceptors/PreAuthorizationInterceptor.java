package io.upit.guice.security.interceptors;

import com.google.inject.Inject;
import com.google.inject.Injector;
import io.upit.guice.security.MethodAuthorizer;
import io.upit.guice.security.PreAuthorize;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class PreAuthorizationInterceptor implements MethodInterceptor {

    private final Injector injector;

    @Inject
    public PreAuthorizationInterceptor(Injector injector) {
        this.injector = injector;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        PreAuthorize preAuthorize = method.getDeclaredAnnotation(PreAuthorize.class);
        if(null == preAuthorize){
            return invocation.proceed();
        }

        for(Class<? extends MethodAuthorizer> methodAuthorizerClass : preAuthorize.methodAuthorizers()) {
            MethodAuthorizer methodAuthorizer = injector.getInstance(methodAuthorizerClass);

            // Throws an exception if there is a problem.
            methodAuthorizer.authorizeMethodInvocation(invocation);
        }

        return invocation.proceed();
    }

}
