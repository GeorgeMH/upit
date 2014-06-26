package io.upit.dal.jdbi.mysql;

import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

public abstract class UserDAOImpl implements UserDAO {

	@Override
	public abstract String create(User session);

	@Override
	public abstract void update(User session);

	@Override
	public abstract void delete(User authSession);

	@Override
	public abstract void deleteById(String IdentifierType);

	@Override
	public abstract User getById(String IdentifierType);

}
