package br.com.democracy.service;

import java.util.List;

import br.com.democracy.dto.QuestionEditDTO;
import br.com.democracy.dto.QuestionInputDTO;
import br.com.democracy.dto.QuestionOutputDTO;
import br.com.democracy.dto.QuestionSearchDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;

public interface QuestionService {

	void newQuestion(QuestionInputDTO question);

	void activateQuestion(Long questionId) throws ServiceException;

	void deactivateQuestion(Long questionId) throws ServiceException;

	List<QuestionOutputDTO> searchQuestion(QuestionSearchDTO search)
			throws ServiceException;

	QuestionOutputDTO getQuestionDetails(Long questionId)
			throws ServiceException;

	void editQuestion(QuestionEditDTO edit) throws ValidationException;

}
