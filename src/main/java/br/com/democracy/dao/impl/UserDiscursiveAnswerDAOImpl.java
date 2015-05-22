package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.UserDiscursiveAnswerDAO;
import br.com.democracy.persistence.UserDiscursiveAnswer;

@Repository
public class UserDiscursiveAnswerDAOImpl extends
		GenericDAOImpl<UserDiscursiveAnswer> implements UserDiscursiveAnswerDAO {

	@Override
	protected Class<UserDiscursiveAnswer> getClazz() {
		return UserDiscursiveAnswer.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c,
			UserDiscursiveAnswer example) {
	}

}
