package io.upit.security;

import io.upit.dal.models.security.AuthenticationMetaData;
import io.upit.dal.models.security.LoginRequest;

public interface AuthenticationProvider {

    boolean authenticate(LoginRequest loginRequest, AuthenticationMetaData authMetaData);

}
