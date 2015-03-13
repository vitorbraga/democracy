package br.com.democracy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.democracy.dao.GenericDAO;
import br.com.democracy.dto.ListDTO;

/**
 * The Class GenericDAOImpl.
 * 
 * @param <Entity>
 *            the generic type
 */
public abstract class GenericDAOImpl<Entity> implements GenericDAO<Entity> {

	/** The hibernate session factory. */
	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * This method is used to get the class of the entity of this DAO. This is
	 * necessary because is impossible to get the Generic type.
	 * 
	 * @return the clazz
	 */
	protected abstract Class<Entity> getClazz();

	/**
	 * Builds the Criteria query.
	 * 
	 * @return the criteria
	 */
	protected Criteria buildQuery() {
		return sessionFactory.getCurrentSession().createCriteria(getClazz());
	}

	@Override
	public void delete(Entity entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public ListDTO<Entity> findByExample(Entity entity, Integer start,
			Integer limit) {
		Example example = buildExample(entity);

		example = example.enableLike(MatchMode.ANYWHERE).ignoreCase();

		Criteria c = buildQuery();

		c.add(example);

		addPropertiedToCriteria(c, entity);

		return getPaginatedListFromCriteria(c, start, limit);
	}

	protected Example buildExample(Entity entity) {
		Example example = Example.create(entity);
		return example;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entity getById(Long id) {
		Entity entity = (Entity) sessionFactory.getCurrentSession().get(
				getClazz(), id);
		return entity;
	}
	
	@Override
	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> getByIdList(List<Long> id) {
		List<Entity> entity = (List<Entity>) sessionFactory.getCurrentSession()
				.createCriteria(getClazz()).add(Restrictions.in("id", id))
				.list();
		return entity;
	}

	@Override
	public Entity saveOrUpdate(Entity entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}
	
	@Override
	public Entity save(Entity entity) {
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}
	
	
	/* Save Or Update a entire list
	 * @param <Entity>
	 * 			the generic type
	 * @param entityList
	 * 			a list to be saved
	 */
	@Override
	public void saveOrUpdateAll(List<Entity> entityList) {
		Session session = sessionFactory.getCurrentSession();
		for (Entity entity : entityList) {
			session.saveOrUpdate(entity);
		}
		return;
	}

	/**
	 * Execute the query and returns the paginated result.
	 * 
	 * @param <Entity>
	 *            the generic type
	 * @param query
	 *            the query 
	 * @param start
	 *            the start
	 * @param size
	 *            the size
	 * @return the paginated list from criteria
	 */
	@SuppressWarnings("unchecked")
	protected static <Entity> ListDTO<Entity> getPaginatedListFromCriteria(
			Criteria query, Integer start, Integer size) {

		final ListDTO<Entity> listDTO = new ListDTO<Entity>();

		final ScrollableResults scroll = query.scroll();

		// If there is at least one result on the scroll.
		if (scroll.last()) {
			// Gets the total number of rows on the search.
			listDTO.setSize(scroll.getRowNumber() + 1L);

			// Add the current result to the DTO set and advance to the next
			// one. Repeat at most 'size' times.
			Long count = 0L;
			scroll.setRowNumber(start);
			if(start.compareTo(listDTO.getSize().intValue()) < 0){
				do {
	
					Object o = scroll.get(0);
	
					listDTO.getList().add((Entity) o);
	
				} while ((++count < size) && scroll.next());
			}
		}

		return listDTO;
	}

	/**
	 * Add all alias necessary to build the query using the example entity.
	 * 
	 * @param c
	 *            the c
	 * @param example
	 *            the example
	 */
	protected abstract void addPropertiedToCriteria(Criteria c, Entity example);

	@SuppressWarnings("unchecked")
	public List<Entity> getAll() {
		Criteria c = buildQuery();

		return c.list();
	}

	@Override
	public ListDTO<Entity> findByExample(Entity entity, Integer start,
			Integer limit, String order) {

		Example example = buildExample(entity);

		example = example.enableLike(MatchMode.ANYWHERE).ignoreCase();

		Criteria c = buildQuery();

		c.add(example);

		c.addOrder(Order.asc(order));

		addPropertiedToCriteria(c, entity);

		return getPaginatedListFromCriteria(c, start, limit);
	}

	protected Criteria findByExample(Entity entity) {

		Example example = buildExample(entity);

		example = example.enableLike(MatchMode.ANYWHERE).ignoreCase();

		Criteria c = buildQuery();

		c.add(example);

		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entity findUniqueByExample(Entity entity) {

		Example example = buildExample(entity);

		example = example.enableLike(MatchMode.ANYWHERE).ignoreCase();

		Criteria c = buildQuery();

		c.add(example);
		
		addPropertiedToCriteria(c, entity);

		return (Entity) c.uniqueResult();
	}

}
