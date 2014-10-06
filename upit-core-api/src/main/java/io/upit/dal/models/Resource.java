package io.upit.dal.models;

import java.util.Date;

public interface Resource<IDType> {

    public IDType getId();
    public void setId(IDType id);

    public int getVersion();
    public void setVersion(int version);

    public Date getCreated();
    public void setCreated(Date dateCreated);

}
