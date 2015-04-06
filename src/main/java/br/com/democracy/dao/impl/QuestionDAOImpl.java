package br.com.democracy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dto.QuestionSearchDTO;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> searchQuestion(QuestionSearchDTO question) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Question.class);

		if (question.getStatus() != null && !question.getStatus().equals("0")) {
			criteria.add(Restrictions.eq("status", Integer.parseInt(question.getStatus())));
		}

		/*
		if (question.getTheme() != null && !question.getTheme().equals("0")) {
			criteria.add(Restrictions.eq("theme",
					Integer.parseInt(question.getTheme())));
		}*/

		return criteria.list();
	}
}
