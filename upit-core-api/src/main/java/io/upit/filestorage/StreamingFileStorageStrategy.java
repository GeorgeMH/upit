package io.upit.filestorage;

        import io.upit.dal.models.UploadedFile;

        import java.io.InputStream;

public interface StreamingFileStorageStrategy {

    UploadedFile storeFile(UploadedFile uploadedFile, InputStream inputStream) throws FileStorageException ;

    InputStream retrieveFile(UploadedFile uploadedFile);

}
