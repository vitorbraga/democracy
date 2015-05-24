package br.com.democracy.dao;

import java.util.List;

import br.com.democracy.dto.QuestionSearchDTO;
import br.com.democracy.persistence.Question;

public interface QuestionDAO extends GenericDAO<Question> {

	List<Question> searchQuestion(QuestionSearchDTO question, boolean isAdmin);

	List<Question> getAvailableQuestions();
}
