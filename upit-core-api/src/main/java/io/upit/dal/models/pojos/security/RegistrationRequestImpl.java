package io.upit.dal.models.pojos.security;

import io.upit.dal.models.User;
import io.upit.dal.models.security.RegistrationRequest;

public class RegistrationRequestImpl implements RegistrationRequest {

    private User requestedUser;
    private String password;
    private String authenticationType;

    public User getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(User requestedUser) {
        this.requestedUser = requestedUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }
}
