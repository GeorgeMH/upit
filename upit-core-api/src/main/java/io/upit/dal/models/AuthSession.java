package io.upit.dal.models;

import java.util.Date;

public interface AuthSession extends Resource<String> {

    public String getUserId();
    public void setUserId(String userId);

    public Date getCreated();
    public void setCreated(Date created);

    public Date getExpires();
    public void setExpires(Date expires);

    public Date getLastAccessed();
    public void setLastAccessed(Date lastAccessed);

    public boolean isActive();
    public void setActive(boolean active);

}
