package io.upit.security;

public class AuthorizationException extends UpitSecurityException {

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
