package io.upit.dal.models.pojos;

import io.upit.dal.models.AuthSession;

import java.util.Date;
import java.util.Objects;

public class AuthSessionImpl implements AuthSession {
    
    private String sessionId;
    private String userId;
    private Date created;
    private Date expires;
    private Date lastAccessed;
    private boolean active;

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public java.util.Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Date getExpires() {
        return expires;
    }

    @Override
    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public Date getLastAccessed() {
        return lastAccessed;
    }

    @Override
    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSessionId());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AuthSession)){
            return false;
        }
        AuthSession check = (AuthSession)obj;
        return Objects.equals(getSessionId(), check.getSessionId());
    }

}
