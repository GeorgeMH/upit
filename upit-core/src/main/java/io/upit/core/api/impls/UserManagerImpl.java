package io.upit.core.api.impls;

import io.upit.core.api.UserManager;
import io.upit.core.dal.dao.UserDAO;
import io.upit.core.dal.models.User;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.google.common.base.Strings;
import com.google.inject.Inject;

public class UserManagerImpl implements UserManager {
	private static final int ITERATIONS = 2048;
	private static final int KEY_LENGTH = 256; // bits

	private final UserDAO userDao;

	@Inject
	public UserManagerImpl(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public User register(User user) {
		if (Strings.isNullOrEmpty(user.getEmail())) {
			throw new IllegalStateException("Email is not set");
		}

		// Always force the email to be lower case to make DB searches faster
		// TODO: Should we validate emails?
		user.setEmail(user.getEmail().toLowerCase());

		if (Strings.isNullOrEmpty(user.getPassword())) {
			throw new IllegalStateException("Password is not set");
		}

		String passwordSalt = UUID.randomUUID().toString();
		String hash;
		try {
			hash = hashPassword(user.getPassword(), passwordSalt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new IllegalStateException("Failed hashing password for user: " + user, e);
		}
		user.setPassword(passwordSalt + "$" + hash);

		user.setDateCreated(new Date());
		return userDao.create(user);
	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = userDao.getUserByEmail(email);

		String realPassword = user.getPassword();

		int saltEndIdx = realPassword.indexOf("$");
		if (-1 == saltEndIdx) {
			throw new IllegalStateException("User has a password that is missing a salt, no idea how to authenticate him.");
		}

		String salt = realPassword.substring(0, saltEndIdx);

		String saltedHashValue;
		try {
			saltedHashValue = hashPassword(password, salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new IllegalStateException("Failed hashing password to authenticate user " + email, e);
		}

		if ((salt + "$" + saltedHashValue).equals(realPassword)) {
			// We authenticated ! yay
			return user;
		}
		// Failed to authenticate
		return null;
	}

	@Override
	public User getUserById(long id) {
		return userDao.getById(id);
	}

	public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// We use $ as a special separator character to store the salt within
		// the same password field in the db.
		salt = salt.replaceAll("$", "");

		char[] passwordChars = password.toCharArray();
		byte[] saltBytes = salt.getBytes();

		PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
		SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hashedPassword = key.generateSecret(spec).getEncoded();
		return String.format("%x", new BigInteger(hashedPassword));
	}
}
