package io.upit.dal.jpa.models.security;

import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AbstractResource;
import io.upit.dal.models.security.AuthenticationMetaData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "AuthenticationMetaData")
public class JpaAuthenticationMetaData extends AbstractResource<Long> implements AuthenticationMetaData {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;

    @Column(nullable = true)
    private String userNameOrEmail;

    @Column
    private String saltedPassword;

    @Column
    private String salt;

    @Column
    private String authenticationType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }

    @Override
    public void setUserNameOrEmail(String userName) {
        this.userNameOrEmail = userName;
    }

    @Override
    public String getSaltedPassword() {
        return saltedPassword;
    }

    @Override
    public void setSaltedPassword(String password) {
        this.saltedPassword = password;
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String getAuthenticationType() {
        return authenticationType;
    }

    @Override
    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
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

    public static JpaAuthenticationMetaData wrapToJpa(AuthenticationMetaData metaData) {
        if (null == metaData) {
            return null;
        } else if (metaData instanceof JpaAuthenticationMetaData) {
            return (JpaAuthenticationMetaData) metaData;
        }

        //This should rarely happen due to DI, should we log/assert it and blow up instead?
        JpaAuthenticationMetaData ret = new JpaAuthenticationMetaData();
        ret.setId(metaData.getId());
        ret.setUserId(metaData.getUserId());
        ret.setSaltedPassword(metaData.getSaltedPassword());
        ret.setSalt(metaData.getSalt());
        ret.setAuthenticationType(metaData.getAuthenticationType());

        return ret;
    }

}
