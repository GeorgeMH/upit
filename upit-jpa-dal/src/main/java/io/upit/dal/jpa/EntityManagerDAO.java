package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.DAO;
import io.upit.dal.models.Resource;
import io.upit.utils.mapping.PojoInterfaceMapper;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EntityManagerDAO<DataObject extends Resource<ID>, ID extends Serializable> implements DAO<DataObject, ID> {

    private final Class<DataObject> dataClassType;
    private final Class<? extends DataObject> jpaClassType;
    protected final EntityManager entityManager;

    @Inject
    public EntityManagerDAO(Class<DataObject> dataClassType, Class<? extends DataObject> jpaClassType, EntityManager entityManager) {
        this.dataClassType = dataClassType;
        this.jpaClassType = jpaClassType;
        this.entityManager = entityManager;
    }

    public <DataObjectImpl extends DataObject> DataObject autoBoxForJpa(DataObjectImpl obj) {
        if (null == obj) {
            return null;
        } else if (jpaClassType.isInstance(obj)) {
            return obj;
        }
        return PojoInterfaceMapper.mapSiblingClass(dataClassType, jpaClassType, obj);
    }

    public <DataObjectImpl extends DataObject> List<DataObject> autoBoxForJpa(List<DataObjectImpl> objs) {
        List<DataObject> ret = new LinkedList<>();
        for (DataObjectImpl impl : objs) {
            ret.add(PojoInterfaceMapper.mapSiblingClass(dataClassType, jpaClassType, impl));
        }
        return ret;
    }


    @Override
    public DataObject getById(ID id) {
        return entityManager.find(jpaClassType, id);
    }

    @Override
    public DataObject create(DataObject entity) {
        if (null == entity) {
            throw new NullPointerException("Entity may not be null");
        }
        entity = autoBoxForJpa(entity);

        entity.setCreated(new Date());
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public DataObject update(DataObject entity) {
        entity = autoBoxForJpa(entity);
        return entityManager.merge(entity);
    }

    @Override
    public DataObject delete(DataObject entity) {
        entity = autoBoxForJpa(entity);
        return deleteById(entity.getId());
    }

    @Override
    public DataObject deleteById(ID id) {
        DataObject dataObject = getById(id);
        if (null != dataObject) {
            delete(dataObject);
        }
        return dataObject;
    }

}
