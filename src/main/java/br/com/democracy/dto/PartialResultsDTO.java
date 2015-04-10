package br.com.democracy.dto;

import java.util.List;

public class PartialResultsDTO {

	/** Total de respostas */
	private Integer total;
	
	private List<AnswerOutputDTO> answers;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<AnswerOutputDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerOutputDTO> answers) {
		this.answers = answers;
	}
	
}
