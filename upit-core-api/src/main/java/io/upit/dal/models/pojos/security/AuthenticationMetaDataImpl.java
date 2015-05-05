package io.upit.dal.models.pojos.security;


import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AbstractResource;
import io.upit.dal.models.security.AuthenticationMetaData;

import java.util.Objects;

public class AuthenticationMetaDataImpl extends AbstractResource<Long> implements AuthenticationMetaData {

    private Long userId;
    private String password;
    private String salt;
    private String authenticationProviderURI;

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getSalt(){
        return this.salt;
    }

    @Override
    public void setSalt(String salt){
        this.salt = salt;
    }

    @Override
    public String getAuthenticationProviderURI() {
        return authenticationProviderURI;
    }

    @Override
    public void setAuthenticationProviderURI(String authenticationMethod) {
        this.authenticationProviderURI = authenticationMethod;
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
