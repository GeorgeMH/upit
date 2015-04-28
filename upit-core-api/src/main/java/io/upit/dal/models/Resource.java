package io.upit.dal.models;

import java.util.Date;

public interface Resource<IDType> {

    IDType getId();
    void setId(IDType id);

    int getVersion();
    void setVersion(int version);

    Date getCreated();
    void setCreated(Date dateCreated);

}
