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
	 * @param authSession the auth session
	 * @return the auth session as it is represented in the database.
	 */
	public AuthSession validateSession(AuthSession authSession);

	/**
	 * Ends the specified AuthSession, making it inactive.
	 *
	 * @param authSession the auth session
	 */
	public void endSession(AuthSession authSession);
}
