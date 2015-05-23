package io.upit.dal;

import io.upit.dal.models.Resource;

import java.io.Serializable;

public interface DAO<DataObject extends Resource<IDType>, IDType extends Serializable> {

    DataObject create(DataObject data) throws UpitDAOException;

    DataObject update(DataObject data) throws UpitDAOException;

    DataObject delete(DataObject data) throws UpitDAOException;

    DataObject deleteById(IDType id) throws UpitDAOException;

    DataObject getById(IDType id);

}
