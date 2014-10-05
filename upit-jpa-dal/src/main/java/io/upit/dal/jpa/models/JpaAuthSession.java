package io.upit.dal.jpa.models;

import io.upit.dal.models.AuthSession;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

/**
 * Created by george on 10/5/14.
 */
@Entity(name="AuthSession")
public class JpaAuthSession implements AuthSession {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Version
    private int version;

    private String userId;
    private Date created;
    private Date expires;
    private Date lastAccessed;
    private boolean active;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public int getVersion(){
        return version;
    }

    public void setVersion(int version){
        this.version = version;
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

}
