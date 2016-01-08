package io.upit.filestorage;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.upit.dal.models.FileType;
import io.upit.dal.models.UploadedFile;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LocalDiskFileStorageStrategy implements StreamingFileStorageStrategy {
    private final Logger logger = LoggerFactory.getLogger(LocalDiskFileStorageStrategy.class);

    private final File uploadedFileRepository;

    @Inject
    public LocalDiskFileStorageStrategy(@Named("upitLocalDiskFileRepository") File uploadedFileRepository) {
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public UploadedFile storeFile(UploadedFile uploadedFile, InputStream inputStream) throws FileStorageException {
        MessageDigest messageDigest = createNewMessageDigest();

        File targetTempFile = null;
        try (DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest)) {
            targetTempFile = File.createTempFile("upit-", "uploadingFile", uploadedFileRepository);
            Files.copy(digestInputStream, targetTempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            if (null != targetTempFile) {
                targetTempFile.delete();
            }
            throw new FileStorageException("Failed writing file to temporary target: " + targetTempFile.getAbsolutePath(), e);
        }

        String fileHash = (new HexBinaryAdapter().marshal(messageDigest.digest()));

        if (null == fileHash || "".equals(fileHash.trim())) {
            targetTempFile.delete();
            throw new FileStorageException("Failed calculating uploaded file hash (unknown)");
        }

        uploadedFile.setFileHash(fileHash);
        uploadedFile.setFileSize(targetTempFile.length());

        detectType(uploadedFile, targetTempFile);

        File finalFileName = new File(uploadedFileRepository, fileHash);

        if (!finalFileName.exists()) {
            targetTempFile.renameTo(finalFileName);
        } else {
            // duplicate file
            targetTempFile.delete();
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

    private MessageDigest createNewMessageDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }
    }

    private void detectType(UploadedFile uploadedFile, File targetTempFile) throws FileStorageException {
        // Best effort file type detection
        try (InputStream detectorInputStream = new BufferedInputStream(new FileInputStream(targetTempFile))) {
            Metadata metaData = new Metadata();
            if (null != uploadedFile.getFileName()) {
                metaData.set(Metadata.RESOURCE_NAME_KEY, uploadedFile.getFileName());
            }
            if (null != uploadedFile.getContentType()) {
                metaData.set(Metadata.CONTENT_TYPE, uploadedFile.getContentType());
            }

            MediaType mediaType = new MimeTypes().detect(detectorInputStream, metaData);
            if (null != mediaType) {
                // Override the client supplied mime type
                uploadedFile.setContentType(mediaType.toString());
                uploadedFile.setFileType(FileType.getFileType(mediaType.toString()));

                MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
                try {
                    MimeType mimeType = allTypes.forName(mediaType.toString());
                    if (null != mimeType && null != mimeType.getExtension() && !"".equals(mimeType.getExtension())) {
                        uploadedFile.setExtension(mimeType.getExtension());
                    }
                } catch (MimeTypeException e) {
                    logger.debug("Failed detecting file type", e);
                }
            } else {
                uploadedFile.setFileType(FileType.UNKNOWN);
            }

            if (null == uploadedFile.getExtension() || "".equals(uploadedFile.getExtension())) {
                int idx = -1;
                if (null != uploadedFile.getFileName() && (idx = uploadedFile.getFileName().indexOf(".")) > 0) {
                    uploadedFile.setExtension(uploadedFile.getFileName().substring(idx));
                }
            }
        } catch (IOException e) {
            targetTempFile.delete();
            throw new FileStorageException("Failed analyzing saved file", e);
        }
    }
}
