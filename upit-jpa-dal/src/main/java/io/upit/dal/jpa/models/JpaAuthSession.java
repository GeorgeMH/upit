package io.upit.dal.jpa.models;

import io.upit.dal.models.AuthSession;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

@Entity(name = "AuthSession")
public class JpaAuthSession implements AuthSession {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Version
    private int version;

    @Column(nullable = true)
    private Long userId;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = true)
    private Date expires;

    @Column(nullable = true)
    private Date lastValidated;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean anonymous;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Date getCreated() {
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
    public Date getLastValidated() {
        return lastValidated;
    }

    @Override
    public void setLastValidated(Date lastValidated) {
        this.lastValidated = lastValidated;
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
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
