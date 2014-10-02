package io.upit.dal.jdbi;

import io.upit.dal.UserDAO;
import io.upit.dal.models.User;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public abstract class UserDAOImpl implements UserDAO {

    @Override
    @GetGeneratedKeys
    @SqlUpdate("jdbi/h2/User.create.sql")
    public abstract String create(@BindBean User session);

    @Override
    @SqlUpdate("jdbi/h2/User.update.sql")
    public abstract void update(@BindBean User session);

    @Override
    @SqlUpdate("jdbi/h2/User.deleteById.sql")
    public abstract void delete(@BindBean User authSession);

    @Override
    @SqlUpdate("jdbi/h2/User.deleteById.sql")
    public abstract void deleteById(@Bind("id") String IdentifierType);

    @Override
    @SqlQuery("jdbi/h2/User.getById.sql")
    public abstract User getById(@Bind("id") String IdentifierType);

    @Override
    @SqlQuery("jdbi/h2/AuthSession.getByUserNameOrEmail.sql")
    public abstract User getByUserNameOrEmail(@Bind("input") String input);

}
