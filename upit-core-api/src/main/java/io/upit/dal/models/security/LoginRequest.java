package io.upit.dal.models.security;

public interface LoginRequest {

    String getUserNameOrEmail();

    void setUserNameOrEmail(String userNameOrEmail);

    String getPassword();

    void setPassword(String password);

    String getRequestType();

    void setRequestType(String requestType);

}
