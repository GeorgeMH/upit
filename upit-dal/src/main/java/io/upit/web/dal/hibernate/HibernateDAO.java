package io.upit.web.dal.hibernate;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

public class HibernateDAO<TYPE, ID> {

	private final Class<TYPE> daoClassType;

	private final EntityManager entityManager;

	@Inject
	public HibernateDAO(EntityManager entityManager, Class<TYPE> daoClassType) {
		this.entityManager = entityManager;
		this.daoClassType = daoClassType;
	}

	public TYPE getById(ID id) {
		return entityManager.find(daoClassType, id);
	}
}
