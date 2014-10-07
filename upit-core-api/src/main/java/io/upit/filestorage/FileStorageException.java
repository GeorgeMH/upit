package io.upit.filestorage;

public class FileStorageException extends Exception {

    public FileStorageException() {

    }

    public FileStorageException(String s){
        super(s);
    }

    public FileStorageException(String s, Throwable t){
        super(s, t);
    }

}
