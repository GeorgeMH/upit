package io.upit.core.jpa.api.dal.dao;

import io.upit.core.jpa.api.dal.EntityManagerDAO;
import io.upit.core.jpa.api.dal.models.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class UserDAO extends EntityManagerDAO<JpaUser, Long> {

	@Inject
	public UserDAO(EntityManager entityManager) {
		super(entityManager, JpaUser.class);
	}

	@Transactional
	public JpaUser getUserByEmail(String email) {
		// Emails should always be forced to be lower case before being inserted
		TypedQuery<JpaUser> query = entityManager.createQuery("SELECT user FROM User user WHERE user.email = :email", JpaUser.class);
		query.setParameter("email", email.toLowerCase());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
