package io.upit.dal.jpa.models;

import io.upit.dal.models.FileType;
import io.upit.dal.models.UploadedFile;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "UploadedFile")
public class JpaUploadedFile implements UploadedFile {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    @Column(nullable = false)
    private Date created;

    @Column(unique = true)
    private String hash;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = true)
    private String fileName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date dateCreated) {
        this.created = dateCreated;
    }

    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public void setHash(String hash) {
        this.hash = hash;
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
