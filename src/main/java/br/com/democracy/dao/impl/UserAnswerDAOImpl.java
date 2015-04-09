package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.UserAnswerDAO;
import br.com.democracy.persistence.UserAnswer;

@Repository
public class UserAnswerDAOImpl extends GenericDAOImpl<UserAnswer> implements
		UserAnswerDAO {

	@Override
	protected Class<UserAnswer> getClazz() {
		return UserAnswer.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, UserAnswer example) {
	}

}
