package io.upit.jaxrs.dto;

import io.upit.dal.models.User;
import io.upit.dal.models.security.AuthenticationMetaData;

public class RegistrationRequest {

    private User requestedUser;
    private AuthenticationMetaData authenticationMetaData;

    public User getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(User requestedUser) {
        this.requestedUser = requestedUser;
    }

    public AuthenticationMetaData getAuthenticationMetaData() {
        return authenticationMetaData;
    }

    public void setAuthenticationMetaData(AuthenticationMetaData authenticationMetaData) {
        this.authenticationMetaData = authenticationMetaData;
    }
}
