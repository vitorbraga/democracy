package br.com.democracy.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	private static final long serialVersionUID = 3260774000214528827L;

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "ACADEMIC_REGISTER", nullable = true)
	private String academicRegister;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "GENDER", nullable = false)
	private Integer gender;
	
	@Column(name = "STATUS", nullable = false)
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ACTIVE")
	private Date dateActive;
	
	/** Ver UserTypeEnum */
	@Column(nullable = false)
	private Integer type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Comment> comments;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDateActive() {
		return dateActive;
	}

	public void setDateActive(Date dateActive) {
		this.dateActive = dateActive;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
}
