package io.upit.security;

import com.google.inject.Inject;
import io.upit.dal.models.AuthSession;

public class RequestSession {

    private final AuthSession authSession;

    @Inject
    public RequestSession(AuthSession authSession) {
        this.authSession = authSession;
    }

    public AuthSession getAuthSession() {
        return authSession;
    }

}
