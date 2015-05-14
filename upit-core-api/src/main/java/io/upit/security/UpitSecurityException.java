package io.upit.security;


import io.upit.UpitServiceException;

public class UpitSecurityException extends UpitServiceException {

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
