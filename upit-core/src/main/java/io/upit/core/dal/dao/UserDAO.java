package io.upit.core.dal.dao;

import io.upit.core.dal.models.User;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserDAO extends EntityManagerDAO<User, Integer> {

	@Inject
	public UserDAO(EntityManager entityManager) {
		super(entityManager, User.class);
	}

}
