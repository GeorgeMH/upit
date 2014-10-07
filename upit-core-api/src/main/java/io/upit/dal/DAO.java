package io.upit.dal;

import io.upit.dal.models.Resource;

import java.io.Serializable;

public interface DAO<DataObject extends Resource<IDType>, IDType extends Serializable> {

    public DataObject create(DataObject data) throws UpitDAOException;

    public DataObject update(DataObject data) throws UpitDAOException;

    public DataObject delete(DataObject data) throws UpitDAOException;

    public DataObject deleteById(IDType id) throws UpitDAOException;

    public DataObject getById(IDType id);

}
