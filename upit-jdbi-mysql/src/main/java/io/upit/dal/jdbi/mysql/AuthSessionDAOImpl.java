package io.upit.dal.jdbi.mysql;

import io.upit.dal.AuthSessionDAO;
import io.upit.dal.models.AuthSession;

public abstract class AuthSessionDAOImpl implements AuthSessionDAO {

	@Override
	public abstract String create(AuthSession data);

	@Override
	public abstract void update(AuthSession data);

	@Override
	public abstract void delete(AuthSession data);

	@Override
	public abstract void deleteById(String id);

	@Override
	public abstract AuthSession getById(String id);

}
