package io.upit.core.jpa.api.dal;


/**
 * DAO represents a basic Data Access Object
 * 
 * @param <TYPE> TYPE that the DAO is accessing
 * @param <ID> TYPE of the ID field on the specified TYPE class.
 */
public interface DAO<TYPE, ID> {

	/**
	 * Gets TYPE object by its ID.
	 *
	 * @param id the id of the object
	 * @return the associated object
	 */
	public TYPE getById(ID id);

	/**
	 * Creates the entity.
	 *
	 * @param entity the entity
	 * @return the type
	 */
	public TYPE create(TYPE entity);

	/**
	 * Updates the entity.
	 *
	 * @param entity the entity
	 * @return the type
	 */
	public TYPE update(TYPE entity);

	/**
	 * Delete entity.
	 *
	 * @param entity the entity
	 */
	public void delete(TYPE entity);

}
