package io.upit.web.dal.mongo;

import io.upit.web.dal.UserDAO;
import io.upit.web.dal.models.User;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.Datastore;
import com.google.inject.Inject;

public class MongoUserDAO extends AbstractMongoDAO<User, ObjectId> implements UserDAO {
	
	@Inject
	public MongoUserDAO(Datastore dataStore) {
		super(User.class, dataStore);
	}

}
