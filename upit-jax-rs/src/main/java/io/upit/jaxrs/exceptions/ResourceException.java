package io.upit.jaxrs.exceptions;

public class ResourceException extends Exception {

    private Class<?> resourceClass;

    public ResourceException() {
        super();
    }

    public ResourceException(String msg) {
        super(msg);
    }

    public ResourceException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
