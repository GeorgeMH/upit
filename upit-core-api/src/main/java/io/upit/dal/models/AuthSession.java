package io.upit.dal.models;

import java.util.Date;

public interface AuthSession extends Resource<String> {

    Long getUserId();
    void setUserId(Long userId);

    Date getCreated();
    void setCreated(Date created);

    Date getExpires();
    void setExpires(Date expires);

    Date getLastAccessed();
    void setLastAccessed(Date lastAccessed);

    boolean isActive();
    void setActive(boolean active);

    boolean isAnonymous();
    void setAnonymous(boolean isAnonymous);

}
