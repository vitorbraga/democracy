package br.com.democracy.dto;

import java.util.List;

public class QuestionAvailableOutputDTO {

	private String questionId;
	
	private String question;
	
	private List<AnswerOutputDTO> answers;
	
	private String userAnswer;
	
	private List<CommentOutputDTO> comments;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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
	
	
}
