package io.upit.dal.models.pojos;

import io.upit.dal.models.AuthSession;

import java.util.Date;
import java.util.Objects;

public class AuthSessionImpl extends AbstractResource<String> implements AuthSession {

    private Long userId;
    private Date expires;
    private Date lastAccessed;
    private boolean active;
    private boolean anonymous;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public boolean isAnonymous() {
        return anonymous;
    }

    @Override
    public void setAnonymous(boolean isAnonymous) {
        this.anonymous = isAnonymous;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AuthSession)){
            return false;
        }
        AuthSession check = (AuthSession)obj;
        return Objects.equals(getId(), check.getId());
    }

}
