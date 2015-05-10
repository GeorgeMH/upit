package io.upit.dal.models.pojos.security;

import io.upit.dal.models.security.LoginRequest;

public class LoginRequestImpl implements LoginRequest {

    private String userNameOrEmail;

    private String password;

    private String requestType;

    @Override
    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }

    @Override
    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
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
    public String getRequestType() {
        return requestType;
    }

    @Override
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

}
