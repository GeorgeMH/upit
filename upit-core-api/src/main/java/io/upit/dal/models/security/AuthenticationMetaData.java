package io.upit.dal.models.security;

import io.upit.dal.models.Resource;

public interface AuthenticationMetaData extends Resource<Long> {

    Long getUserId();
    void setUserId(Long userId);

    String getUserName();
    void setUserName(String username);

    String getPassword();
    void setPassword(String password);

    String getSalt();
    void setSalt(String salt);

    // Todo, somehow return an Authentication
    String getAuthenticationProviderURI();
    void setAuthenticationProviderURI(String authenticationProviderURI);

}
