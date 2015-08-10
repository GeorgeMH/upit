package io.upit.security;


import io.upit.UpitServiceException;

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
