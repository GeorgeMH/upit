package io.upit.core.jpa.api;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.api.UserManager;
import io.upit.core.api.models.AuthSession;
import io.upit.core.api.models.LoginRequest;
import io.upit.core.api.models.User;
import io.upit.core.jpa.api.dal.dao.AuthSessionDAO;
import io.upit.core.jpa.api.dal.models.JpaAuthSession;

import java.util.Date;
import java.util.UUID;

import com.google.inject.Inject;

public class JpaAuthSessionManager implements AuthSessionManager {

	private final AuthSessionDAO authSessionDao;
	private final UserManager userManager;

	@Inject
	public JpaAuthSessionManager(AuthSessionDAO authSessionDAO, UserManager userManager) {
		this.authSessionDao = authSessionDAO;
		this.userManager = userManager;
	}

	@Override
	public AuthSession login(LoginRequest loginRequest) {
		// TODO: Support multi-source logins and account associations

		User user = userManager.authenticateUser(loginRequest.getUserName(), loginRequest.getPassWord());
		if (null == user) {
			return null;
		}

		JpaAuthSession session = new JpaAuthSession();
		session.setSessionId(UUID.randomUUID().toString());
		session.setCreated(new Date());
		session.setActive(true);
		session.setLastAccessed(new Date());
		session.setUserId("" + user.getId());

		JpaAuthSession createdSession = authSessionDao.create(session);

		return createdSession;
	}

	@Override
	public JpaAuthSession validateSession(AuthSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endSession(AuthSession session) {
		// TODO Auto-generated method stub

	}

}
