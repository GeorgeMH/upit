package io.upit.dal.models.security;

public interface AuthenticationProvider {

    AuthenticationResult authenticateReuqest(AuthenticationMetaData request);

}
