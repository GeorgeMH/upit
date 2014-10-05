package io.upit.dal.models;

import java.util.Date;

public interface User extends Resource<String>{

    public String getUserName();
    void setUserName(String userName);

    public String getEmail();
    public void setEmail(String email);

    public String getPassword();
    public void setPassword(String password);

    public Date getCreated();
    public void setCreated(Date dateCreated);

}
