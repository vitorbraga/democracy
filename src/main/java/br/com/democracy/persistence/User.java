package br.com.democracy.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	private static final long serialVersionUID = 3260774000214528827L;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "ACADEMIC_REGISTER", nullable = false, unique = true)
	private String academicRegister;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "GENDER", nullable = false)
	private Integer gender;
	
	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Comment> comments;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcademicRegister() {
		return academicRegister;
	}

	public void setAcademicRegister(String academicRegister) {
		this.academicRegister = academicRegister;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
}
