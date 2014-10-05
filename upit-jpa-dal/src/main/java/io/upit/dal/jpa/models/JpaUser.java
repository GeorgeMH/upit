package io.upit.dal.jpa.models;

import io.upit.dal.models.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;

@Entity(name = "User")
public class JpaUser implements User {

    @Id
    private String id;

    @Version
    private int version;

    private String userName;

    private String email;

    private String password;

    private Date created;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public int getVersion(){
        return version;
    }

    public void setVersion(int version){
        this.version = version;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

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

}