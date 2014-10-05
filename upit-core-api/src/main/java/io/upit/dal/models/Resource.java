package io.upit.dal.models;

public interface Resource<IDType> {

    public IDType getId();
    public void setId(IDType id);

    public int getVersion();
    public void setVersion(int version);

}
