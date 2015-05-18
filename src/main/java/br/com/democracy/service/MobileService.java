package br.com.democracy.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.democracy.dto.CommentOutputDTO;
import br.com.democracy.dto.PartialResultsDTO;
import br.com.democracy.dto.QuestionAvailableOutputDTO;
import br.com.democracy.exception.ServiceException;

public interface MobileService {

	String authenticate(String email, String password, HttpSession session)
			throws ServiceException;

	List<QuestionAvailableOutputDTO> getAvailableQuestions(String token)
			throws ServiceException;

	void makeComment(String token, Long questionId, String comment)
			throws ServiceException;

	void answerQuestion(String token, Long questionId, Long answerId)
			throws ServiceException;

	PartialResultsDTO getPartialResults(String token, Long questionId)
			throws ServiceException;

	List<CommentOutputDTO> getQuestionComments(String token, Long questionId)
			throws ServiceException;

}
