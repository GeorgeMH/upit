package io.upit.dal.models;

import java.util.Date;

public interface User extends Resource<Long> {

    String getIdHash();
    void setIdHash(String str);

    String getUserName();
    void setUserName(String userName);

    String getEmail();
    void setEmail(String email);

    Date getCreated();
    void setCreated(Date dateCreated);

}
