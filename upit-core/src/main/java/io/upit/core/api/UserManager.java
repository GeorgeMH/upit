package io.upit.core.api;

import io.upit.core.dal.models.User;

/**
 * The UserManager, responsible for all business logic related to managing User accounts.
 */
public interface UserManager {

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	public User getUserById(long id);

	/**
	 * Register.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User register(User user);

	/**
	 * Returns the user specified by the username if the password is a match
	 *
	 * @param username the username
	 * @param password the password
	 * @return the user
	 */
	public User authenticateUser(String username, String password);

}
