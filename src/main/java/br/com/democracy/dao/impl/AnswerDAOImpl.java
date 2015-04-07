package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.AnswerDAO;
import br.com.democracy.persistence.Answer;

@Repository
public class AnswerDAOImpl extends GenericDAOImpl<Answer> implements AnswerDAO {

	@Override
	protected Class<Answer> getClazz() {
		return Answer.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, Answer example) {
	}
}
