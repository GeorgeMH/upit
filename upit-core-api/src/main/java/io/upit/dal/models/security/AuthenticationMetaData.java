package io.upit.dal.models.security;

import io.upit.dal.models.Resource;

public interface AuthenticationMetaData extends Resource<Long> {

    Long getId();
    void setId(Long id);

    Long getUserId();
    void setUserId(Long userId);

    String getPassword();
    void setPassword(String password);

    String getAuthenticationProviderURI();
    void setAuthenticationProviderURI(String authenticationProviderURI);

}
