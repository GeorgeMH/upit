package io.upit.core.dal.dao;

import io.upit.core.dal.models.AuthSession;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class AuthSessionDAO extends EntityManagerDAO<AuthSession, String> {

	@Inject
	public AuthSessionDAO(EntityManager entityManager) {
		super(entityManager, AuthSession.class);
	}

	/**
	 * Returns the AuthSession that is active, has a matching sessionId, and has
	 * a matching AuthToken string, or returns null if no such match exists.
	 * 
	 * @param sessionthe session
	 * 
	 * @return the active matching session
	 */
	@Transactional
	public AuthSession getActiveMatchingSession(AuthSession session) {
		TypedQuery<AuthSession> query = entityManager.createQuery("SELECT authSession FROM AuthSession authSession WHERE authSession.active = true " +
																"AND authSession.sessionId = :sessionId " +
																"AND authSession.authToken = :authToken", AuthSession.class);
		query.setParameter("sessionId", session.getSessionId());
		query.setParameter("authToken", session.getAuthToken());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
