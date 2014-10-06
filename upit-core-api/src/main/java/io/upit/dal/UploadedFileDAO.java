package io.upit.dal;

import io.upit.dal.models.UploadedFile;

public interface UploadedFileDAO extends DAO<UploadedFile, Long> {

    public UploadedFile getByHash(String fileHash);

}
