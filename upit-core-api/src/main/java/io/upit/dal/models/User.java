package io.upit.dal.models;

import java.util.Date;

public interface User {

    public String getId();
    public void setId(String id);
    
    public String getUserName();
    void setUserName(String userName);

    public String getEmail();
    public void setEmail(String email);

    public String getPassword();
    public void setPassword(String password);

    public Date getDateCreated();
    public void setDateCreated(Date dateCreated);

}
