package io.upit.dal;

import io.upit.dal.models.UploadedFile;

import java.io.InputStream;

public interface UploadedFileDAO extends DAO<UploadedFile, Long> {

    UploadedFile create(UploadedFile uploadedFile, InputStream inputStream) throws UpitDAOException;

    InputStream getFileStream(UploadedFile uploadedFile);

    UploadedFile getByFileHash(String fileHash);

    UploadedFile getByIdHash(String shortHash);

}
