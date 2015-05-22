package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.DiscursiveAnswerDAO;
import br.com.democracy.persistence.DiscursiveAnswer;

@Repository
public class DiscursiveAnswerDAOImpl extends GenericDAOImpl<DiscursiveAnswer>
		implements DiscursiveAnswerDAO {

	@Override
	protected Class<DiscursiveAnswer> getClazz() {
		return DiscursiveAnswer.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, DiscursiveAnswer example) {
	}
}
