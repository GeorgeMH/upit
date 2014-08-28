package io.upit.dal.models.pojos;

import io.upit.dal.models.LoginRequest;

import java.util.Objects;

public class LoginRequestImpl implements LoginRequest {

    private String userName;
    private String password;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
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
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LoginRequest)) {
            return false;
        }
        return Objects.equals(getUserName(), ((LoginRequest) obj).getUserName());
    }

}
