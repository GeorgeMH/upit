package io.upit.dal.models;


public interface UploadedFile extends Resource<Long> {


    public String getIdHash();
    public void setIdHash(String hash);

    public String getFileHash();
    public void setFileHash(String hash);

    public FileType getFileType();
    public void setFileType(FileType fileType);

    public String getContentType();
    public void setContentType(String contentType);

    public Long getFileSize();
    public void setFileSize(Long fileSize);

    public String getFileName();
    public void setFileName(String fileName);

}
