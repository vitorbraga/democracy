package br.com.democracy.dto;

import java.util.List;

public class PartialResultsDTO {

	/** Total de respostas */
	private Integer total;
	
	private Integer type;
	
	private List<AnswerOutputDTO> answers;
	
	private List<DiscursiveAnswerOutputDTO> discursiveAnswers;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<DiscursiveAnswerOutputDTO> getDiscursiveAnswers() {
		return discursiveAnswers;
	}

	public void setDiscursiveAnswers(
			List<DiscursiveAnswerOutputDTO> discursiveAnswers) {
		this.discursiveAnswers = discursiveAnswers;
	}
	
}
