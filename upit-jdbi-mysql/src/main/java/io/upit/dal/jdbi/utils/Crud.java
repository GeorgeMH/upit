package io.upit.dal.jdbi.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.skife.jdbi.v2.sqlobject.SqlStatementCustomizingAnnotation;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

@SqlStatementCustomizingAnnotation(UseStringTemplate3StatementLocator.LocatorFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Crud {

}
