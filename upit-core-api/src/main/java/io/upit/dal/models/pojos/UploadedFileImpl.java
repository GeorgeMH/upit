package io.upit.dal.models.pojos;

import io.upit.dal.models.FileType;
import io.upit.dal.models.UploadedFile;

public class UploadedFileImpl extends AbstractResource<Long> implements UploadedFile{

    private String idHash;
    private String fileHash;
    private String extension;
    private FileType fileType;
    private String contentType;
    private Long fileSize;
    private String fileName;

    @Override
    public String getIdHash(){
        return idHash;
    }

    @Override
    public void setIdHash(String hash){
        this.idHash = hash;
    }

    @Override
    public String getFileHash() {
        return fileHash;
    }

    @Override
    public void setFileHash(String hash) {
        this.fileHash = hash;
    }

    @Override
    public String getExtension(){
        return extension;
    }

    @Override
    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public FileType getFileType() {
        return fileType;
    }

    @Override
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String getContentType(){
        return contentType;
    }

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    @Override
    public Long getFileSize() {
        return fileSize;
    }

    @Override
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
