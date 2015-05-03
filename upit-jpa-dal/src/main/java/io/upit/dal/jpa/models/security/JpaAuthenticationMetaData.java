package io.upit.dal.jpa.models.security;

import io.upit.dal.jpa.models.JpaUser;
import io.upit.dal.models.User;
import io.upit.dal.models.security.AuthenticationMetaData;

import javax.persistence.*;

@Entity(name="AuthenticationMetaData")
public class JpaAuthenticationMetaData implements AuthenticationMetaData {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private JpaUser user;

    @Column
    private String password;

    @Column
    private String authenticationMethod;

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = JpaUser.wrapUser(user);
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
