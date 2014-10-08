package io.upit.dal;

import io.upit.dal.models.UploadedFile;

import java.io.InputStream;

public interface UploadedFileDAO extends DAO<UploadedFile, Long> {

    public UploadedFile create(UploadedFile uploadedFile, InputStream inputStream) throws UpitDAOException;

    public InputStream getFileStream(UploadedFile uploadedFile);

    public UploadedFile getByFileHash(String fileHash);

    public UploadedFile getByIdHash(String shortHash);

}
