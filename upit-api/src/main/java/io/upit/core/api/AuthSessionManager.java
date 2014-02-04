package io.upit.core.api;

import io.upit.core.api.models.AuthSession;
import io.upit.core.api.models.LoginRequest;

/**
 * The Interface for creating/validating/ending AuthSessions
 */
public interface AuthSessionManager {

	/**
	 * Attempt to login with the specified credentials. Returns a newly created
	 * AuthSession object if successful, null on failure.
	 * 
	 * @param loginRequest the login request object
	 * @return the AuthSession if successful, otherwise null
	 */
	public AuthSession login(LoginRequest loginRequest);

	/**
	 * Validate session is still valid and active.
	 *
	 * @param session            the session
	 * @return the auth session
	 */
	public AuthSession validateSession(AuthSession session);

	/**
	 * Ends the specified AuthSession, making it inactive.
	 *
	 * @param session            the session to end
	 */
	public void endSession(AuthSession session);
}
