package io.upit.dal.jpa.models;

import io.upit.dal.models.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;
import java.util.Objects;

@Entity(name = "User")
public class JpaUser implements User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String idHash;

    @Version
    private int version;

    @Column(unique = true)
    private String userName;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private Date created;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getIdHash() {
        return idHash;
    }

    @Override
    public void setIdHash(String idHash) {
        this.idHash = idHash;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    public static JpaUser wrapUser(User user) {
        if (null == user) {
            return null;
        } else if (user instanceof JpaUser) {
            return (JpaUser) user;
        }

        //This should rarely happen due to DI, should we log/assert it?
        JpaUser ret = new JpaUser();
        ret.setId(user.getId());
        ret.setVersion(user.getVersion());
        ret.setEmail((user.getEmail()));
        ret.setIdHash(user.getIdHash());
        ret.setCreated(user.getCreated());
        return ret;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        //  Identity equals
        return Objects.equals(getId(), ((User) obj).getId());
    }
}
