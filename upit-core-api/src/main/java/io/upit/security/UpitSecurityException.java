package io.upit.security;

public class UpitSecurityException extends RuntimeException {

    public UpitSecurityException() {
        super();
    }

    public UpitSecurityException(String msg) {
        super(msg);
    }

    public UpitSecurityException(String msg, Throwable inner) {
        super(msg, inner);
    }

}
