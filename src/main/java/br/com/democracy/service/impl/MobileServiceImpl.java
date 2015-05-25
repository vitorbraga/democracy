package br.com.democracy.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.democracy.dao.UserDAO;
import br.com.democracy.dto.CommentOutputDTO;
import br.com.democracy.dto.PartialResultsDTO;
import br.com.democracy.dto.QuestionAvailableOutputDTO;
import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.DateHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.persistence.User;
import br.com.democracy.persistence.enums.UserStatusEnum;
import br.com.democracy.persistence.enums.UserTypeEnum;
import br.com.democracy.service.MobileService;
import br.com.democracy.service.QuestionService;

@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private QuestionService questionService;

	@Override
	@Transactional(readOnly = false)
	public String authenticate(String email, String password,
			HttpSession session) throws ServiceException, ParseException {

		User user = userDAO.getUserByEmail(email);

		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		if (user != null) {
			if (!user.getType().equals(UserTypeEnum.NORMAL.id())
					|| !user.getStatus().equals(UserStatusEnum.ACTIVE.id())) {
				throw new ServiceException("Usuário sem permissão.");
			}

			if (encoder.matches(password, user.getPassword())) {
				session.setAttribute("user", user);
				// create token
				String token = ConvertHelper.convertIdToView(user.getId());
				token += generateUniqueSerial(DateHelper.now());
				user.setToken(token);
				user.setUpdated(DateHelper.now());
				userDAO.saveOrUpdate(user);

				return token;
			} else {
				throw new ServiceException("Senha incorreta");
			}
		} else {
			throw new ServiceException("Usuario não cadastrado");
		}

	}

	public String generateUniqueSerial(Date dateBase) throws ParseException {

		String dateString = DateHelper.formatSerialDate(dateBase);
		/* Cria serial para o torneio */
		String serial = ConvertHelper.generateToken(dateString);

		return serial;
	}
	
	/** Verifica token. Se não encontrar usuário com aquele token 
	 *  a autenticação é invalidada */
	@Transactional(readOnly = true)
	public boolean checkToken(String token) {
		
		User user = userDAO.getUserByToken(token);
		
		if (user == null) {
			return false;
		}
		
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionAvailableOutputDTO> getAvailableQuestions(String token)
			throws ServiceException {

		if (!checkToken(token)) {
			throw new ServiceException(Messages.USER_AUTHENTICATION_INVALID);
		}

		/* Busca perguntas disponiveis */
		List<QuestionAvailableOutputDTO> questions = questionService
				.getAvailableQuestions(true, token);

		return questions;
	}

	@Override
	@Transactional(readOnly = false)
	public void answerQuestion(String token, Long questionId, Long answerId)
			throws ServiceException {

		if (!checkToken(token)) {
			throw new ServiceException(Messages.USER_AUTHENTICATION_INVALID);
		}

		questionService.answerQuestion(questionId, answerId, true, token);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void answerDiscursiveQuestion(String token, Long questionId,
			String answer) throws ServiceException {

		if (!checkToken(token)) {
			throw new ServiceException(Messages.USER_AUTHENTICATION_INVALID);
		}

		questionService.answerDiscursiveQuestion(questionId, answer, true,
				token);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void makeComment(String token, Long questionId, String comment)
			throws ServiceException {

		if (!checkToken(token)) {
			throw new ServiceException(Messages.USER_AUTHENTICATION_INVALID);
		}

		questionService.makeComment(questionId, comment, true, token);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PartialResultsDTO getPartialResults(String token, Long questionId)
			throws ServiceException {

		if (!checkToken(token)) {
			throw new ServiceException(Messages.USER_AUTHENTICATION_INVALID);
		}

		/* Busca perguntas disponiveis */
		PartialResultsDTO partialResults = questionService.getPartialResults(questionId, true, token);

		return partialResults;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CommentOutputDTO> getQuestionComments(String token, Long questionId)
			throws ServiceException {

		if (!checkToken(token)) {
			throw new ServiceException(Messages.USER_AUTHENTICATION_INVALID);
		}

		/* Busca perguntas disponiveis */
		List<CommentOutputDTO> comments = questionService.getComments(
				questionId, true, token);

		return comments;
	}
}
