package io.upit.security;

/**
 * Created by george on 5/19/15.
 */
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
