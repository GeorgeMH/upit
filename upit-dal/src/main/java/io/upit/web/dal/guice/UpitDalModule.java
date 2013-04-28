package io.upit.web.dal.guice;

import io.upit.web.dal.UploadedFileDAO;
import io.upit.web.dal.UserDAO;
import io.upit.web.dal.mongo.MongoUploadedFileDAO;
import io.upit.web.dal.mongo.MongoUserDAO;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.ProvisionException;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class UpitDalModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserDAO.class).to(MongoUserDAO.class);
		bind(UploadedFileDAO.class).to(MongoUploadedFileDAO.class);
	}

	@Provides
	@Singleton
	private MongoClient providesMongoClient() {
		try {
			return new MongoClient(Arrays.asList(new ServerAddress("localhost", 8000)));
		} catch (UnknownHostException e) {
			throw new ProvisionException("Failed creating MongoClient", e);
		}
	}

}
