package io.upit.dal.jdbi.mysql;

import io.upit.dal.PasteDAO;
import io.upit.dal.models.Paste;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public abstract class PasteDAOImpl implements PasteDAO {

    @Override
    @GetGeneratedKeys
    @SqlUpdate("jdbi_sql/Paste.create.sql")
    public abstract String create(@BindBean Paste data);

    @Override
    @SqlUpdate("jdbi_sql/Paste.update.sql")
    public abstract void update(@BindBean Paste data);

    @Override
    @SqlUpdate("jdbi_sql/Paste.deleteById.sql")
    public abstract void delete(@BindBean Paste data);

    @Override
    @SqlUpdate("jdbi_sql/Paste.deleteById.sql")
    public abstract void deleteById(@Bind("id") String id);

    @Override
    @SqlQuery("jdbi_sql/Paste.getById.sql")
    public abstract Paste getById(@Bind("id") String id);

}
