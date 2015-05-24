package br.com.democracy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.caelum.vraptor.ioc.ConverterHandler;
import br.com.democracy.dao.QuestionDAO;
import br.com.democracy.dto.QuestionSearchDTO;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.DateHelper;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.enums.PeriodEnum;
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
		
		if (question.getType() != null && !question.getType().equals("0")) {
			criteria.add(Restrictions.eq("type", Integer.parseInt(question.getType())));
		}
		
		if (question.getDate() != null && !question.getDate().equals("0"))	{
			if(ConvertHelper.convertStringToInteger(question.getDate()).equals(PeriodEnum.LAST_MONTH.id()))	{
				criteria.add(Restrictions.gt("regDate", DateHelper.addDays(DateHelper.now(), -30L)));
			} else if (ConvertHelper.convertStringToInteger(question.getDate()).equals(PeriodEnum.LAST_WEEK.id()))	{
				criteria.add(Restrictions.gt("regDate", DateHelper.addDays(DateHelper.now(), -7L)));
			}
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
