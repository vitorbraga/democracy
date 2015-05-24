package br.com.democracy.service;

import java.util.List;

import br.com.democracy.dto.CommentOutputDTO;
import br.com.democracy.dto.PartialResultsDTO;
import br.com.democracy.dto.QuestionAvailableOutputDTO;
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

	QuestionOutputDTO getQuestionDetails(Long questionId)
			throws ServiceException;

	void editQuestion(QuestionEditDTO edit) throws ValidationException;

	void answerQuestion(Long questionId, Long answerId, boolean isMobile,
			String token) throws ServiceException;

	List<QuestionAvailableOutputDTO> getAvailableQuestions(boolean isMobile,
			String token) throws ServiceException;

	void makeComment(Long questionId, String comment, boolean isMobile,
			String token) throws ServiceException;

	PartialResultsDTO getPartialResults(Long questionId, boolean isMobile,
			String token) throws ServiceException;

	List<CommentOutputDTO> getComments(Long questionId, boolean isMobile,
			String token) throws ServiceException;

	void answerDiscursiveQuestion(Long questionId, String answer,
			boolean isMobile, String token) throws ServiceException;

	QuestionAvailableOutputDTO getQuestionAnswerData(Long questionId,
			boolean isMobile, String token) throws ServiceException;

	List<QuestionOutputDTO> searchQuestion(QuestionSearchDTO search,
			boolean isAdmin) throws ServiceException;

}
