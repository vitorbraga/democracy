package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.UserQuestion;
import br.com.democracy.persistence.enums.QuestionTypeEnum;

public class QuestionAvailableOutputDTO {

	private String id;

	private String question;

	private Integer typeInt;
	
	private String dateActivated;

	private List<AnswerOutputDTO> answers;

	private String userAnswer;
	
	private String userDiscursiveAnswer;

	private Integer numComments;

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

	public Integer getTypeInt() {
		return typeInt;
	}

	public void setTypeInt(Integer typeInt) {
		this.typeInt = typeInt;
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

	public Integer getNumComments() {
		return numComments;
	}

	public void setNumComments(Integer numComments) {
		this.numComments = numComments;
	}
	
	public String getUserDiscursiveAnswer() {
		return userDiscursiveAnswer;
	}

	public void setUserDiscursiveAnswer(String userDiscursiveAnswer) {
		this.userDiscursiveAnswer = userDiscursiveAnswer;
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
				dto.setTypeInt(question.getType());
				dto.setDateActivated(ConvertHelper.dateToViewSlash(question.getDateActivated()));
				
				if(question.getType().equals(QuestionTypeEnum.MULTIPLE_CHOICES.id())) {
					dto.setAnswers(AnswerOutputDTO.copyAll(question.getAnswers()));
					dto.setUserAnswer(checkAnswer(
							question, userQuestions));
				} else {
					dto.setUserDiscursiveAnswer(checkAnswerDiscursive(
							question, userQuestions));
				}
				
				if(question.getComments() != null) {
					dto.setNumComments(question.getComments().size());
				} else {
					dto.setNumComments(0);
				}

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
	
	public static String checkAnswerDiscursive(Question question,
			List<UserQuestion> userQuestions) throws ServiceException {

		if (userQuestions != null) {
			for (UserQuestion userQuestion : userQuestions) {
				if (userQuestion.getQuestion().getId().equals(question.getId())) {
					// respondeu essa pergunta
					return userQuestion.getDiscursiveAnswer();
				}
			}
		}

		return null;
	}
}
