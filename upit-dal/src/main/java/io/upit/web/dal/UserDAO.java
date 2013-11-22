package io.upit.web.dal;

import io.upit.web.dal.hibernate.HibernateDAO;
import io.upit.web.dal.models.User;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

public class UserDAO extends HibernateDAO<User, Integer> {

	@Inject
	public UserDAO(EntityManager entityManager) {
		super(entityManager, User.class);
	}

}
