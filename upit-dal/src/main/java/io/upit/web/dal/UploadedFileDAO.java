package io.upit.web.dal;

import io.upit.web.dal.models.UploadedFile;

import org.bson.types.ObjectId;

import com.google.inject.Singleton;

@Singleton
public interface UploadedFileDAO extends DAO<UploadedFile, ObjectId> {

}
