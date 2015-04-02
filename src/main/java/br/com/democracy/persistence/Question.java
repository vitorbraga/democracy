package br.com.democracy.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Question extends BaseEntity {

	private static final long serialVersionUID = 3260774000214528827L;

	@Column(name = "QUESTION", nullable = false)
	private String question;
	
	/** QuestionStatusEnum */
	@Column(name = "STATUS", nullable = false)
	private Integer status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	private List<Answer> answers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	private List<Comment> comments;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
