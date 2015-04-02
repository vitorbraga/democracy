package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.CommentDAO;
import br.com.democracy.persistence.Comment;

@Repository
public class CommentDAOImpl extends GenericDAOImpl<Comment> implements
		CommentDAO {

	@Override
	protected Class<Comment> getClazz() {
		return Comment.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, Comment example) {
	}
}
