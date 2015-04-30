package io.upit.dal.models.pojos;

import io.upit.dal.models.User;

import java.util.Objects;

public class UserImpl extends AbstractResource<Long> implements io.upit.dal.models.User {

    private String userName;
    private String email;
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        //  Identity equals
        return Objects.equals(getId(), ((User) obj).getId());
    }
}
