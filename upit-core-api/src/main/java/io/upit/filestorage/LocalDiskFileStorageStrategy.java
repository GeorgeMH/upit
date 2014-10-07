package io.upit.filestorage;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.upit.dal.models.UploadedFile;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LocalDiskFileStorageStrategy implements StreamingFileStorageStrategy {
    private final Logger logger = LoggerFactory.getLogger(LocalDiskFileStorageStrategy.class);

    private final File uploadedFileRepository;

    @Inject
    public LocalDiskFileStorageStrategy(@Named("upitLocalDiskFileRepository") File uploadedFileRepository){
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public UploadedFile storeFile(UploadedFile uploadedFile, InputStream inputStream) throws FileStorageException {
        MessageDigest messageDigest = createNewMessageDigest();
        DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest);

        File targetFile = null;
        try {
            targetFile = File.createTempFile("upit-", "uploadingFile", uploadedFileRepository);
            Files.copy(digestInputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e) {
            if(null != targetFile) {
                targetFile.delete();
            }
            throw new FileStorageException("Failed writing file to temporary target: " + targetFile.getAbsolutePath(), e);
        }

        String fileHash = (new HexBinaryAdapter().marshal(messageDigest.digest()));

        if(null == fileHash || "".equals(fileHash.trim())) {
            targetFile.delete();
            throw new FileStorageException("Failed calculating uploaded file hash (unknown)");
        }

        uploadedFile.setFileHash(fileHash);
        uploadedFile.setFileSize(targetFile.length());

        File finalFileName = new File(uploadedFileRepository, fileHash);

        if(!finalFileName.exists()) {
            //TODO: this won't work across file systems, we are safe here since its all in the same directory for now.
            targetFile.renameTo(finalFileName);
        } else {
            // duplicate file
            targetFile.delete();
        }

        return uploadedFile;
    }

    @Override
    public InputStream retrieveFile(UploadedFile uploadedFile) {
        try {
            return new FileInputStream(new File(uploadedFileRepository, uploadedFile.getFileHash()));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private MessageDigest createNewMessageDigest(){
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("", e);
        }
    }

}
