package br.com.democracy.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWER")
public class Answer extends BaseEntity {
	
	private static final long serialVersionUID = -5393347434212764723L;
	
	@Column(name = "ANSWER", nullable = false)
	private String answer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "QUESTION_ID")
	private Question question;
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
