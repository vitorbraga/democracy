package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.UserDAO;
import br.com.democracy.persistence.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	@Override
	protected Class<User> getClazz() {
		return User.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, User example) {
	}
	
	@Override
	public User getUserByEmail(String email) {
		
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));

		return (User) criteria.uniqueResult();
		
	}
	
}
