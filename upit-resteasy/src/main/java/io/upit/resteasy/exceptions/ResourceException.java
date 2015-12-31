package io.upit.resteasy.exceptions;

public class ResourceException extends RuntimeException {

//    private Class<?> resourceClass;

    public ResourceException() {

    }

    public ResourceException(String msg) {
        super(msg);
    }

    public ResourceException(String msg, Throwable inner) {
        super(msg, inner);
    }

}
