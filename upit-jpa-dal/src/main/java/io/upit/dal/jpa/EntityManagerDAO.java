package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.DAO;
import io.upit.dal.models.Resource;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;

public class EntityManagerDAO<DataObject extends Resource<ID>, ID extends Serializable> implements DAO<DataObject, ID> {

    private final Class<? extends DataObject> daoClassType;
    protected final EntityManager entityManager;

    @Inject
    public EntityManagerDAO(Class<? extends DataObject> daoClassType, EntityManager entityManager) {
        this.daoClassType = daoClassType;
        this.entityManager = entityManager;
    }

    @Override
    public DataObject getById(ID id) {
        return entityManager.find(daoClassType, id);
    }

    @Override
    public DataObject create(DataObject entity) {
        entity.setCreated(new Date());
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public DataObject update(DataObject entity) {
        return entityManager.merge(entity);
    }

    @Override
    public DataObject delete(DataObject entity) {
        return deleteById(entity.getId());
    }

    @Override
    public DataObject deleteById(ID id){
        DataObject dataObject = getById(id);
        if(null != dataObject) {
            delete(dataObject);
        }
        return dataObject;
    }

}
