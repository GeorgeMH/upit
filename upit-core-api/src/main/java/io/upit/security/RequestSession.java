package io.upit.security;

import io.upit.dal.models.AuthSession;

public class RequestSession {

    private final AuthSession authSession;

    public RequestSession(AuthSession authSession) {
        this.authSession = authSession;
    }

    public AuthSession getAuthSession() {
        return authSession;
    }

}
