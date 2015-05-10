package io.upit.services;

import com.google.inject.Inject;
import io.upit.UpitServiceException;
import io.upit.dal.UploadedFileDAO;
import io.upit.dal.models.UploadedFile;

import java.io.*;

public class UploadedFileService extends AbstractResourceService<UploadedFile, Long> {

    private final UploadedFileDAO uploadedFileDAO;

    @Inject
    public UploadedFileService(UploadedFileDAO uploadedFileDAO) {
        super(UploadedFile.class, uploadedFileDAO);
        this.uploadedFileDAO = uploadedFileDAO;
    }

    public InputStream getFileStream(UploadedFile uploadedFile) throws UpitServiceException {
        return uploadedFileDAO.getFileStream(uploadedFile);
    }


    public UploadedFile uploadFile(UploadedFile fileToUpload, InputStream uploadFileStream) throws UpitServiceException {
        // TODO: Can we move content type-detection out of the DAO layer and into the Service layer where it belongs?
        // We should also have the ability to associate an UploadedFile with a specific/arbitrary StreamingFileStorageStrategy
        UploadedFile parsedUploadedFile = uploadedFileDAO.create(fileToUpload, uploadFileStream);
        return parsedUploadedFile;
    }


    public UploadedFile getByIdHash(String shortHash) {
        return uploadedFileDAO.getByIdHash(shortHash);
    }


}
