package io.upit.dal.models;


public interface UploadedFile extends Resource<Long> {

    String getIdHash();

    void setIdHash(String hash);

    String getExtension();

    void setExtension(String extension);

    String getFileHash();

    void setFileHash(String hash);

    FileType getFileType();

    void setFileType(FileType fileType);

    String getContentType();

    void setContentType(String contentType);

    Long getFileSize();

    void setFileSize(Long fileSize);

    String getFileName();

    void setFileName(String fileName);

}
