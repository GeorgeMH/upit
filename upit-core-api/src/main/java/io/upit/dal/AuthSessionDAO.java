package io.upit.dal;

import io.upit.dal.models.AuthSession;

/**
 * The Interface for creating/validating/ending AuthSessions
 */
public interface AuthSessionDAO extends AbstractDAO<AuthSession, String> {

    public AuthSession getByUserNameOrEmail(String input);
}
