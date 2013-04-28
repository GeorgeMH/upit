package io.upit.web.dal.mongo;

import io.upit.web.dal.DAO;

import com.github.jmkgreen.morphia.Datastore;
import com.google.inject.Inject;

public class AbstractMongoDAO<TYPE, KEY> implements DAO<TYPE, KEY> {
	
	private final Class<TYPE> daoType;
	private final Datastore dataStore;
	
	@Inject
	public AbstractMongoDAO(Class<TYPE> daoType, Datastore dataStore) {
		this.daoType = daoType;
		this.dataStore = dataStore;
	}

	public Class<TYPE> getDaoType() {
		return daoType;
	}

	@Override
	public void save(TYPE model) {
		dataStore.save(model);
	}

	@Override
	public void delete(TYPE model) {
		dataStore.delete(model);
	}

	@Override
	public void deleteByKey(KEY key) {
		dataStore.delete(daoType, key);
	}

	@Override
	public TYPE getByKey(KEY key) {
		return dataStore.get(daoType, key);
	}

}
