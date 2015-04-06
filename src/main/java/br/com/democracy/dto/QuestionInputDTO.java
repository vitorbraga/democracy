package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.democracy.helper.DateHelper;
import br.com.democracy.messages.Messages;
import br.com.democracy.persistence.Answer;
import br.com.democracy.persistence.Question;
import br.com.democracy.persistence.enums.QuestionStatusEnum;
import br.com.democracy.validation.annotation.Validate;
import br.com.democracy.validation.annotation.ValidateCollection;

public class QuestionInputDTO {

	@Validate(message=Messages.QUESTION_FIELD_INVALID, validation="isQuestionOrAnswer")
	private String question;
	
	@ValidateCollection(recursive = true, nullable = false)
	private List<AnswerInputDTO> answers;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswerInputDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerInputDTO> answers) {
		this.answers = answers;
	}

	public static Question copy(QuestionInputDTO questionDTO) {
		
		if(questionDTO != null) {
			
			String regUser = "admin@email.com";
			Date now = DateHelper.now();
			
			Question question = new Question();
			question.setQuestion(questionDTO.getQuestion());
			question.setStatus(QuestionStatusEnum.INACTIVE.id());
			question.setRegDate(now);
			question.setUpdated(now);
			question.setRegUser(regUser);
			
			question.setAnswers(new ArrayList<Answer>());
			
			/* Constroi lista de alternativas (respostas) */
			for(AnswerInputDTO ansDTO : questionDTO.getAnswers()) {
				Answer answer = new Answer();
				answer.setAnswer(ansDTO.getAnswer());
				answer.setRegUser(regUser);
				answer.setRegDate(now);
				answer.setUpdated(now);
				answer.setQuestion(question);
				
				question.getAnswers().add(answer);
			}
			
			return question;
		}
		
		return null;
	}
	
}
