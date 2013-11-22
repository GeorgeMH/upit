package io.upit.web.dal.hibernate;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public abstract class HibernateDAO<TYPE, ID> {

	private final Class<TYPE> daoClassType;

	private final EntityManager entityManager;

	@Inject
	public HibernateDAO(EntityManager entityManager, Class<TYPE> daoClassType) {
		this.entityManager = entityManager;
		this.daoClassType = daoClassType;
	}

	/**
	 * Gets entity by id.
	 * 
	 * @param id the id of the entity
	 * @return the by id
	 */
	@Transactional
	public TYPE getById(ID id) {
		return entityManager.find(daoClassType, id);
	}

	/**
	 * Creates the object in the persistence context.
	 *
	 * @param entity the entity
	 * @return the type
	 */
	@Transactional
	public TYPE create(TYPE entity) {
		entityManager.persist(entity);
		return entity;
	}

	/**
	 * Update object in persistence context.
	 *
	 * @param entity the entity
	 * @return the type
	 */
	@Transactional
	public TYPE update(TYPE entity) {
		return entityManager.merge(entity);
	}

	@Transactional
	public void delete(TYPE entity) {
		entityManager.remove(entity);
	}
}

