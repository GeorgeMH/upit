package io.upit.core.dal.dao;

import io.upit.core.dal.DAO;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * The Class EntityManagerDAO.
 *
 * @param <TYPE> the generic type
 * @param <ID> the generic type
 */
public abstract class EntityManagerDAO<TYPE, ID> implements DAO<TYPE, ID> {

	/** The DAO class type. */
	private final Class<TYPE> daoClassType;

	/** The entity manager. */
	protected final EntityManager entityManager;

	/**
	 * Instantiates a new EntityManager DAO object for the specified TYPE.
	 *
	 * @param entityManager the entity manager
	 * @param daoClassType the dao class type
	 */
	@Inject
	public EntityManagerDAO(EntityManager entityManager, Class<TYPE> daoClassType) {
		this.entityManager = entityManager;
		this.daoClassType = daoClassType;
	}

	/* (non-Javadoc)
	 * @see io.upit.core.dal.DAO#getById(java.lang.Object)
	 */
	@Override
	@Transactional
	public TYPE getById(ID id) {
		return entityManager.find(daoClassType, id);
	}

	/* (non-Javadoc)
	 * @see io.upit.core.dal.DAO#create(java.lang.Object)
	 */
	@Override
	@Transactional
	public TYPE create(TYPE entity) {
		entityManager.persist(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see io.upit.core.dal.DAO#update(java.lang.Object)
	 */
	@Override
	@Transactional
	public TYPE update(TYPE entity) {
		return entityManager.merge(entity);
	}

	/* (non-Javadoc)
	 * @see io.upit.core.dal.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(TYPE entity) {
		entityManager.remove(entity);
	}
}

