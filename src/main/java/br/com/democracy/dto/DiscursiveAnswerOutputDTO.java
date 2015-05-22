package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.DiscursiveAnswer;

public class DiscursiveAnswerOutputDTO {

	private String id;

	private String answer;

	private String date;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static List<DiscursiveAnswerOutputDTO> copyAll(List<DiscursiveAnswer> discursiveAnswers)
			throws ServiceException {

		if (discursiveAnswers != null) {

			List<DiscursiveAnswerOutputDTO> dtos = new ArrayList<DiscursiveAnswerOutputDTO>();
			for (DiscursiveAnswer answer : discursiveAnswers) {
				DiscursiveAnswerOutputDTO dto = new DiscursiveAnswerOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(answer.getId()));
				dto.setAnswer(answer.getAnswer());
				dto.setDate(ConvertHelper.dateToView(answer.getRegDate()));

				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

}
