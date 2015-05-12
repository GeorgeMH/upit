package io.upit;

public class UpitServiceException extends Exception {

    public UpitServiceException() {
    }

    public UpitServiceException(String msg) {
        super(msg);
    }

    public UpitServiceException(String msg, Throwable inner) {
        super(msg, inner);
    }

}
