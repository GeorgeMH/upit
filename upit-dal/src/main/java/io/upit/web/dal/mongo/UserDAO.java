package io.upit.web.dal.mongo;

import io.upit.web.dal.models.User;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.google.inject.Inject;

public class UserDAO extends BasicDAO<User, String> {

	@Inject
	public UserDAO(Datastore dataStore) {
		super(User.class, dataStore);
	}

}
