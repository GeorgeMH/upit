package io.upit.core.api.impls;

import io.upit.core.api.AuthSessionManager;
import io.upit.core.api.UserManager;
import io.upit.core.api.support.LoginRequest;
import io.upit.core.dal.dao.AuthSessionDAO;
import io.upit.core.dal.models.AuthSession;
import io.upit.core.dal.models.User;

import java.util.Date;
import java.util.UUID;

import com.google.inject.Inject;

public class AuthSessionManagerImpl implements AuthSessionManager {

	private final AuthSessionDAO authSessionDao;
	private final UserManager userManager;

	@Inject
	public AuthSessionManagerImpl(AuthSessionDAO authSessionDAO, UserManager userManager) {
		this.authSessionDao = authSessionDAO;
		this.userManager = userManager;
	}

	@Override
	public AuthSession login(LoginRequest loginRequest) {
		// TODO: Support multi-source logins

		User user = userManager.authenticateUser(loginRequest.getUserName(), loginRequest.getPassWord());
		if (null == user) {
			return null;
		}

		AuthSession session = new AuthSession();
		session.setSessionId(UUID.randomUUID().toString());
		session.setCreated(new Date());
		session.setActive(true);
		session.setLastAccessed(new Date());
		session.setUserId("" + user.getId());

		AuthSession createdSession = authSessionDao.create(session);

		return createdSession;
	}

	@Override
	public AuthSession validateSession(AuthSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endSession(AuthSession session) {
		// TODO Auto-generated method stub

	}

}
