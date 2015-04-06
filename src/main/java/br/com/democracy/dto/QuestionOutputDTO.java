package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.Mappers;
import br.com.democracy.persistence.Question;

public class QuestionOutputDTO {

	private String id;

	private String question;

	private List<AnswerOutputDTO> answers;

	private String status;

	private String date;

	private List<SelectBoxDTO> allStatus; 
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public List<SelectBoxDTO> getAllStatus() {
		return allStatus;
	}

	public void setAllStatus(List<SelectBoxDTO> allStatus) {
		this.allStatus = allStatus;
	}

	public static List<QuestionOutputDTO> copyAll(List<Question> questions)
			throws ServiceException {

		if (questions != null) {
			List<QuestionOutputDTO> dtos = new ArrayList<QuestionOutputDTO>();
			for (Question question : questions) {
				QuestionOutputDTO dto = new QuestionOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(question.getId()));
				dto.setQuestion(question.getQuestion());
				dto.setDate(ConvertHelper.dateToView(question.getUpdated()));
				dto.setStatus(Mappers.questionStatus(question.getStatus()));

				// Copia answers
				dto.setAnswers(AnswerOutputDTO.copyAll(question.getAnswers()));
				
				dto.setAllStatus(SelectBoxDTO.copyQuestionStatus());
				dtos.add(dto);
			}
			
			return dtos;
		}

		return null;
	}

	public static QuestionOutputDTO copy(Question question)
			throws ServiceException {

		if (question != null) {
			QuestionOutputDTO dto = new QuestionOutputDTO();
			
			dto.setId(ConvertHelper.convertIdToView(question.getId()));
			dto.setQuestion(question.getQuestion());
			dto.setDate(ConvertHelper.dateToView(question.getUpdated()));
			dto.setStatus(Mappers.questionStatus(question.getStatus()));

			// Copia answers
			dto.setAnswers(AnswerOutputDTO.copyAll(question.getAnswers()));
			dto.setAllStatus(SelectBoxDTO.copyQuestionStatus());
			return dto;
		}

		return null;
	}
}
