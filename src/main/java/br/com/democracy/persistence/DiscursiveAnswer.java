package br.com.democracy.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DISCURSIVE_ANSWER")
public class DiscursiveAnswer extends BaseEntity {
	
	private static final long serialVersionUID = -5393347434212764723L;
	
	@Column(name = "ANSWER", nullable = false)
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
