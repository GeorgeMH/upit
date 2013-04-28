package io.upit.web.dal;


public interface DAO<TYPE, KEY> {

	public void save(TYPE model);

	public void delete(TYPE model);

	public void deleteByKey(KEY key);

	public TYPE getByKey(KEY key);

}
