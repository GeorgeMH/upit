package io.upit.dal.models.pojos;

import io.upit.dal.models.Resource;

import java.util.Date;

public class AbstractResource<IDType> implements Resource<IDType> {

    private IDType id;

    private int version;

    private Date created;


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

    @Override
    public Date getCreated(){
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

}
