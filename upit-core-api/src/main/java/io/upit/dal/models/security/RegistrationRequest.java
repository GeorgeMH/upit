package io.upit.dal.models.security;

import io.upit.dal.models.User;

public interface RegistrationRequest {

    User getRequestedUser();
    void setRequestedUser(User requestedUser);

    String getPassword();
    void setPassword(String password);

    String getAuthenticationType();
    void setAuthenticationType(String authenticationType);
}
