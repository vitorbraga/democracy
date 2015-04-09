package br.com.democracy.dao;

import java.util.List;

import br.com.democracy.persistence.UserQuestion;

public interface UserQuestionDAO extends GenericDAO<UserQuestion> {

	UserQuestion getByUserAndQuestion(Long userId, Long questionId);

	List<UserQuestion> getByQuestion(Long questionId);

	List<UserQuestion> getByUser(Long userId);

}
