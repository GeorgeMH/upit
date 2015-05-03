package io.upit.dal.models.security;

import io.upit.dal.models.User;

public interface AuthenticationMetaData {

    User getUser();
    void setUser(User user);

    String getPassword();
    void setPassword(String password);

    String getAuthenticationMethod();
    void setAuthenticationMethod(String authenticationMethod);
}
