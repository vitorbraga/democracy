package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
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
}
