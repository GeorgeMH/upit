package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.DAO;
import io.upit.dal.models.Resource;
import io.upit.utils.mapping.PojoInterfaceMapper;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EntityManagerDAO<DataObject extends Resource<ID>, JpaDataObject extends DataObject, ID extends Serializable> implements DAO<DataObject, ID> {

    private final Class<DataObject> dataClassType;
    private final Class<JpaDataObject> jpaClassType;
    protected final EntityManager entityManager;

    @Inject
    public EntityManagerDAO(Class<DataObject> dataClassType, Class<JpaDataObject> jpaClassType, EntityManager entityManager) {
        this.dataClassType = dataClassType;
        this.jpaClassType = jpaClassType;
        this.entityManager = entityManager;
    }

    public JpaDataObject autoBoxForJpa(DataObject obj) {
        if (null == obj) {
            return null;
        } else if (jpaClassType.isInstance(obj)) {
            return (JpaDataObject)obj;
        }
        // Map one instance to the other
        return PojoInterfaceMapper.mapSiblingClass(dataClassType, jpaClassType, obj);
    }

    public List<DataObject> autoBoxForJpa(List<DataObject> objs) {
        //TODO: This updates the list in place for performance reasons, this may not be what wew want since this is the DAO layer and not the service layer.
        for (int i = 0; i < objs.size(); i++) {
            objs.set(i, autoBoxForJpa(objs.get(i)));
        }
        return objs;
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
