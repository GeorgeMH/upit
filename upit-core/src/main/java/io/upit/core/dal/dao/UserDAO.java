package io.upit.core.dal.dao;

import io.upit.core.dal.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class UserDAO extends EntityManagerDAO<User, Long> {

	@Inject
	public UserDAO(EntityManager entityManager) {
		super(entityManager, User.class);
	}

	@Transactional
	public User getUserByEmail(String email) {
		// Emails should always be forced to be lower case before being inserted
		TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.email = :email", User.class);
		query.setParameter("email", email.toLowerCase());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
