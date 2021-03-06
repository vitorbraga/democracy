package br.com.democracy.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.persistence.Answer;

public class AnswerOutputDTO {

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

	public static List<AnswerOutputDTO> copyAll(List<Answer> answers)
			throws ServiceException {

		if (answers != null) {

			List<AnswerOutputDTO> dtos = new ArrayList<AnswerOutputDTO>();
			for (Answer answer : answers) {
				AnswerOutputDTO dto = new AnswerOutputDTO();
				dto.setId(ConvertHelper.convertIdToView(answer.getId()));
				dto.setAnswer(answer.getAnswer());

				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

	public static AnswerOutputDTO copy(Answer answer) throws ServiceException {

		if (answer != null) {

			AnswerOutputDTO dto = new AnswerOutputDTO();
			dto.setId(ConvertHelper.convertIdToView(answer.getId()));
			dto.setAnswer(answer.getAnswer());

			return dto;
		}

		return null;
	}
}
