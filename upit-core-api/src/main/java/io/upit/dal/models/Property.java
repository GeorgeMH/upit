package io.upit.dal.models;

public interface Property extends Resource<String> {

    String getType();
    void setType(String type);

    String getValue();
    void setValue(String value);

    String getSource();
    void setSource(String source);

}
