package io.upit.web.dal.guice;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.ProvisionException;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class UpitDalModule extends AbstractModule {

	@Override
	protected void configure() {
	}

	@Provides
	@Singleton
	private Datastore providesMongoClient() {
		try {
			MongoClient ret = new MongoClient(Arrays.asList(new ServerAddress("home.georgemh.com", 27017)));
			ret.setWriteConcern(WriteConcern.JOURNALED);
			return new Morphia().createDatastore(ret, "upit");
		} catch (UnknownHostException e) {
			throw new ProvisionException("Failed creating MongoClient", e);
		}
	}

}
