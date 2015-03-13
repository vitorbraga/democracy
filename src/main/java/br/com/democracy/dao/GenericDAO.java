package br.com.democracy.dao;

import java.util.List;

import br.com.democracy.dto.ListDTO;

/**
 * The Interface GenericDAO. It provides basic CRUD methods for all the DAOs.
 * 
 * @param <Entity>
 *            the generic type
 */
public interface GenericDAO<Entity> {

	/**
	 * Save or update an entity. The behavior of this method is to create a new
	 * entity of the PK of it is null, otherwise the entity will be updated.
	 * 
	 * @param entity
	 *            the entity
	 * @return the entity
	 */
	public Entity saveOrUpdate(Entity entity);
	
	/**
	 * Delete an entity. This is a true deletion, what means that the entity
	 * will be erased in DB. Use it carefully.
	 * 
	 * @param entity
	 *            the entity
	 * @return the boolean
	 */
	public void delete(Entity entity);

	/**
	 * Gets the entity by id.
	 * 
	 * @param id
	 *            the id
	 * @return the entity
	 */
	public Entity getById(Long id);

	/**
	 * Find by example.
	 * 
	 * @param example
	 *            the example
	 * @param start
	 *            the start
	 * @param limit
	 *            the limit
	 * @return the list dto
	 */
	public ListDTO<Entity> findByExample(Entity example, Integer start,
			Integer limit);

	/**
	 * Find by example.
	 * 
	 * @param entity
	 *            the entity
	 * @param start
	 *            the start
	 * @param limit
	 *            the limit
	 * @param order
	 *            the order
	 * @return the list dto
	 */
	public ListDTO<Entity> findByExample(Entity entity, Integer start,
			Integer limit, String order);

	/**
	 * Gets all the records of this entity.
	 * 
	 * @return
	 */
	public List<Entity> getAll();

	public Entity findUniqueByExample(Entity entity);

	public List<Entity> getByIdList(List<Long> id);

	void saveOrUpdateAll(List<Entity> entityList);

	public Entity save(Entity entity);

	void flush();

}
