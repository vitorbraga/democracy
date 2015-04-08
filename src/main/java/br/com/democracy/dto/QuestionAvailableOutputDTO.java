package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.User;

public class QuestionAvailableOutputDTO {

	private String id;

	private String question;

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
			List<Question> questions, User user) throws ServiceException {

		if (questions != null) {

			List<QuestionAvailableOutputDTO> dtos = new ArrayList<QuestionAvailableOutputDTO>();

			for(Question question : questions) {
				
				QuestionAvailableOutputDTO dto = new QuestionAvailableOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(question.getId()));
				dto.setQuestion(question.getQuestion());
				dto.setAnswers(AnswerOutputDTO.copyAll(question.getAnswers()));
				dto.setComments(CommentOutputDTO.copyAll(question.getComments()));
				
				dto.setUserAnswer(""); // TODO verificar se ja respondeu id da answer
				
				dtos.add(dto);
			}
			
			return dtos;
		}

		return null;
	}

}
