package io.upit.dal.models.pojos.security;


import io.upit.dal.models.pojos.AbstractResource;
import io.upit.dal.models.security.AuthenticationMetaData;

import java.util.Objects;

public class AuthenticationMetaDataImpl extends AbstractResource<Long> implements AuthenticationMetaData {

    private Long userId;
    private String userNameOrEmail;
    private String saltedPassword;
    private String salt;
    private String authenticationType;

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUserNameOrEmail() {
        return this.userNameOrEmail;
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
    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    @Override
    public String getSalt() {
        return this.salt;
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
        if (!(obj instanceof AuthenticationMetaData)) {
            return false;
        }
        //  Identity equals
        return Objects.equals(getId(), ((AuthenticationMetaData) obj).getId());
    }
}
