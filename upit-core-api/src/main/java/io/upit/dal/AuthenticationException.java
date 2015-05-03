package io.upit.dal;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String msg) {
        super(msg);
    }

    public AuthenticationException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
