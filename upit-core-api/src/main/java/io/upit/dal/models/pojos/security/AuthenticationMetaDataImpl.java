package io.upit.dal.models.pojos.security;

import io.upit.dal.models.User;
import io.upit.dal.models.security.AuthenticationMetaData;

public class AuthenticationMetaDataImpl implements AuthenticationMetaData {

    private User user;
    private String password;
    private String authenticationMethod;


    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
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
    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    @Override
    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }
}
