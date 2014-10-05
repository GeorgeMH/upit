package io.upit.dal;

import io.upit.dal.models.Resource;

import java.io.Serializable;

public interface DAO<DataObject extends Resource<IDType>, IDType extends Serializable> {

    public DataObject create(DataObject data);

    public DataObject update(DataObject data);

    public DataObject delete(DataObject data);

    public DataObject deleteById(IDType id);

    public DataObject getById(IDType id);

}
