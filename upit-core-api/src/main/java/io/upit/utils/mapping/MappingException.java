package io.upit.utils.mapping;

public class MappingException extends RuntimeException {

    public MappingException() {

    }

    public MappingException(String msg) {
        super(msg);
    }

    public MappingException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
