package io.upit.dal.models.pojos.security;


import io.upit.dal.models.pojos.AbstractResource;
import io.upit.dal.models.security.AuthenticationMetaData;

public class AuthenticationMetaDataImpl extends AbstractResource<Long> implements AuthenticationMetaData {

    private Long userId;
    private String password;
    private String authenticationMethod;

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
    public String getAuthenticationProviderURI() {
        return authenticationMethod;
    }

    @Override
    public void setAuthenticationProviderURI(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }
}
