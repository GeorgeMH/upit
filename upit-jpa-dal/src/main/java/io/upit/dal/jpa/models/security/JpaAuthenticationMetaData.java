package io.upit.dal.jpa.models.security;

import io.upit.dal.models.User;
import io.upit.dal.models.pojos.AbstractResource;
import io.upit.dal.models.security.AuthenticationMetaData;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="AuthenticationMetaData")
public class JpaAuthenticationMetaData extends AbstractResource<Long> implements AuthenticationMetaData {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;

    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private String authenticationProviderURI;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
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
    public String getSalt() {
        return salt;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String getAuthenticationProviderURI() {
        return authenticationProviderURI;
    }

    @Override
    public void setAuthenticationProviderURI(String authenticationMethodURI) {
        this.authenticationProviderURI = authenticationMethodURI;
    }

    public static JpaAuthenticationMetaData wrapToJpa(AuthenticationMetaData metaData) {
        if(null == metaData) {
            return null;
        } else if(metaData instanceof JpaAuthenticationMetaData){
            return (JpaAuthenticationMetaData)metaData;
        }

        //This should rarely happen due to DI, should we log/assert it and blow up instead?
        JpaAuthenticationMetaData ret = new JpaAuthenticationMetaData();
        ret.setId(metaData.getId());
        ret.setUserId(metaData.getUserId());
        ret.setPassword(metaData.getPassword());
        ret.setAuthenticationProviderURI(metaData.getAuthenticationProviderURI());

        return ret;
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
