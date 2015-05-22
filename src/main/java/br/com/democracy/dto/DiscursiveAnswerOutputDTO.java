package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.Answer;

public class DiscursiveAnswerOutputDTO {

	private String id;

	private String answer;

	private Integer chosenTimes;

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

	public Integer getChosenTimes() {
		return chosenTimes;
	}

	public void setChosenTimes(Integer chosenTimes) {
		this.chosenTimes = chosenTimes;
	}

	public static List<DiscursiveAnswerOutputDTO> copyAll(List<Answer> answers)
			throws ServiceException {

		if (answers != null) {

			List<DiscursiveAnswerOutputDTO> dtos = new ArrayList<DiscursiveAnswerOutputDTO>();
			for (Answer answer : answers) {
				DiscursiveAnswerOutputDTO dto = new DiscursiveAnswerOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(answer.getId()));
				dto.setAnswer(answer.getAnswer());

				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

	public static DiscursiveAnswerOutputDTO copy(Answer answer) throws ServiceException {

		if (answer != null) {

			DiscursiveAnswerOutputDTO dto = new DiscursiveAnswerOutputDTO();
			dto.setId(ConvertHelper.convertIdToView(answer.getId()));
			dto.setAnswer(answer.getAnswer());

			return dto;
		}

		return null;
	}
}
