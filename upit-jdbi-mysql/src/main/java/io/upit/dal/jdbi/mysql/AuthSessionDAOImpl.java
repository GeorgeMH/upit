package io.upit.dal.jdbi.mysql;

import io.upit.dal.AuthSessionDAO;
import io.upit.dal.models.AuthSession;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public abstract class AuthSessionDAOImpl implements AuthSessionDAO {

	@Override
	@GetGeneratedKeys
	@SqlUpdate("jdbi_sql/AuthSession.create.sql")
	public abstract String create(@BindBean AuthSession data);

	@Override
	@SqlUpdate("jdbi_sql/AuthSession.update.sql")
	public abstract void update(@BindBean AuthSession data);

	@Override
	@SqlUpdate("jdbi_sql/AuthSession.deleteById.sql")
	public abstract void delete(@BindBean AuthSession data);

	@Override
	@SqlUpdate("jdbi_sql/AuthSession.deleteById.sql")
	public abstract void deleteById(@Bind("id") String id);

	@Override
	@SqlQuery("jdbi_sql/AuthSession.getById.sql")
	public abstract AuthSession getById(@Bind("id") String id);
}
