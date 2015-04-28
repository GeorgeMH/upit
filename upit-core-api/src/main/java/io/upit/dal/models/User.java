package io.upit.dal.models;

import java.util.Date;

public interface User extends Resource<String>{

    String getUserName();
    void setUserName(String userName);

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);

    Date getCreated();
    void setCreated(Date dateCreated);

}
