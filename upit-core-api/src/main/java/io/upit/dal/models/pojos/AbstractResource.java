package io.upit.dal.models.pojos;

import io.upit.dal.models.Resource;

public class AbstractResource<IDType> implements Resource<IDType> {

    private IDType id;

    private int version;


    @Override
    public IDType getId() {
        return id;
    }

    @Override
    public void setId(IDType id) {
        this.id = id;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

}
