package io.upit.core.jpa.api.dal.dao;

import io.upit.core.jpa.api.dal.EntityManagerDAO;
import io.upit.core.jpa.api.dal.models.JpaAuthSession;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class AuthSessionDAO extends EntityManagerDAO<JpaAuthSession, String> {

	@Inject
	public AuthSessionDAO(EntityManager entityManager) {
		super(entityManager, JpaAuthSession.class);
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
	public JpaAuthSession getActiveMatchingSession(JpaAuthSession session) {
		TypedQuery<JpaAuthSession> query = entityManager.createQuery("SELECT authSession FROM AuthSession authSession WHERE authSession.active = true " +
																"AND authSession.sessionId = :sessionId " +
																"AND authSession.authToken = :authToken", JpaAuthSession.class);
		query.setParameter("sessionId", session.getSessionId());
		query.setParameter("authToken", session.getAuthToken());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
