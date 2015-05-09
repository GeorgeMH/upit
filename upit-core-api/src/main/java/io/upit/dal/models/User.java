package io.upit.dal.models;


import io.upit.dal.models.security.AuthenticationMetaData;

public interface User extends Resource<Long> {

    String getIdHash();
    void setIdHash(String idHash);

    String getUserName();
    void setUserName(String userName);

    String getEmail();
    void setEmail(String email);

}
