package io.upit.security;

public class AuthenticationException extends UpitSecurityException {

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
