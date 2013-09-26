package io.upit.web.dal.mongo;

import io.upit.web.dal.models.UploadedFile;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.google.inject.Inject;

public class UploadedFileDAO extends BasicDAO<UploadedFile, String> {

	@Inject
	public UploadedFileDAO(Datastore dataStore) {
		super(UploadedFile.class, dataStore);
	}

}
