package io.upit.dal;

import java.io.Serializable;

public interface AbstractDAO<DataObject, IDType extends Serializable> {

    public IDType create(DataObject data);

    public void update(DataObject data);

    public void delete(DataObject data);

    public void deleteById(IDType id);

    public DataObject getById(IDType id);

}
