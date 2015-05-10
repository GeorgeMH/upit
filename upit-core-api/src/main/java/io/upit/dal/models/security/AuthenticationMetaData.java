package io.upit.dal.models.security;

import io.upit.dal.models.Resource;

public interface AuthenticationMetaData extends Resource<Long> {

    Long getUserId();
    void setUserId(Long userId);

    String getUserNameOrEmail();
    void setUserNameOrEmail(String userNameOrEmail);

    String getSaltedPassword();
    void setSaltedPassword(String password);

    String getSalt();
    void setSalt(String salt);

    // Todo, somehow return an Authentication
    String getAuthenticationType();
    void setAuthenticationType(String authenticationType);

}
