package io.upit.guice.security;

import io.upit.guice.security.authorizers.AllowAllMethodAuthorizer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuthorize {
    Class<? extends MethodAuthorizer> methodAuthorizer()[] default { AllowAllMethodAuthorizer.class };
    int[] argumentIndex() default { 0 };
}
