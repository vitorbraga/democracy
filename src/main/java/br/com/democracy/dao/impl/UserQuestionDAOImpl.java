package br.com.democracy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.UserQuestionDAO;
import br.com.democracy.persistence.UserQuestion;

@Repository
public class UserQuestionDAOImpl extends GenericDAOImpl<UserQuestion> implements UserQuestionDAO {

	@Override
	protected Class<UserQuestion> getClazz() {
		return UserQuestion.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, UserQuestion example) {
	}
	
	@Override
	public UserQuestion getByUserAndQuestion(Long userId, Long questionId) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(UserQuestion.class);
		criteria.add(Restrictions.eq("pk.user.id", userId));
		criteria.add(Restrictions.eq("pk.question.id", questionId));

		return (UserQuestion) criteria.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserQuestion> getByQuestion(Long questionId) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(UserQuestion.class);
		criteria.createAlias("pk.user", "user");
		criteria.add(Restrictions.eq("pk.question.id", questionId));
		
		return (List<UserQuestion>) criteria.list();
	}
	
}
