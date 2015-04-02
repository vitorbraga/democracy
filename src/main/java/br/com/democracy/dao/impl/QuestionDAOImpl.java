package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.persistence.Question;

@Repository
public class QuestionDAOImpl extends GenericDAOImpl<Question> implements
		QuestionDAO {

	@Override
	protected Class<Question> getClazz() {
		return Question.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, Question example) {
	}
}
