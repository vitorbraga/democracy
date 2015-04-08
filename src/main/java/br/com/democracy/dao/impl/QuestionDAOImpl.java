package br.com.democracy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dto.QuestionSearchDTO;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.enums.QuestionStatusEnum;

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

		// TODO buscar por periodo data
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAvailableQuestions() {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Question.class);

		criteria.add(Restrictions.eq("status", QuestionStatusEnum.ACTIVE.id()));

		criteria.addOrder(Order.desc("dateActivated"));
		
		return criteria.list();
	}
}
