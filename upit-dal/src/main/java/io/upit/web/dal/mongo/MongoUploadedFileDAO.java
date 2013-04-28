package io.upit.web.dal.mongo;

import io.upit.web.dal.UploadedFileDAO;
import io.upit.web.dal.models.UploadedFile;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.Datastore;
import com.google.inject.Inject;

public class MongoUploadedFileDAO extends AbstractMongoDAO<UploadedFile, ObjectId> implements UploadedFileDAO {

	@Inject
	public MongoUploadedFileDAO(Datastore dataStore) {
		super(UploadedFile.class, dataStore);
	}

}
