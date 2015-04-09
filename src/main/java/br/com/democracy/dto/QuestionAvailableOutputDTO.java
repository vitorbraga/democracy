package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.UserQuestion;

public class QuestionAvailableOutputDTO {

	private String id;

	private String question;
	
	private String dateActivated;

	private List<AnswerOutputDTO> answers;

	private String userAnswer;

	private List<CommentOutputDTO> comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(String dateActivated) {
		this.dateActivated = dateActivated;
	}

	public List<AnswerOutputDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerOutputDTO> answers) {
		this.answers = answers;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public List<CommentOutputDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentOutputDTO> comments) {
		this.comments = comments;
	}

	public static List<QuestionAvailableOutputDTO> copyAll(
			List<Question> questions, List<UserQuestion> userQuestions)
			throws ServiceException {

		if (questions != null) {

			List<QuestionAvailableOutputDTO> dtos = new ArrayList<QuestionAvailableOutputDTO>();

			for (Question question : questions) {

				QuestionAvailableOutputDTO dto = new QuestionAvailableOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(question.getId()));
				dto.setQuestion(question.getQuestion());
				dto.setDateActivated(ConvertHelper.dateToViewSlash(question.getDateActivated()));
				dto.setAnswers(AnswerOutputDTO.copyAll(question.getAnswers()));
				dto.setComments(CommentOutputDTO.copyAll(question.getComments()));

				dto.setUserAnswer(checkAnswer(
						question, userQuestions));

				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

	public static String checkAnswer(Question question,
			List<UserQuestion> userQuestions) throws ServiceException {

		if (userQuestions != null) {
			for (UserQuestion userQuestion : userQuestions) {
				if (userQuestion.getQuestion().getId().equals(question.getId())) {
					// respondeu essa pergunta
					return ConvertHelper.convertIdToView(userQuestion.getAnswerId());
				}
			}
		}

		return null;
	}
}
