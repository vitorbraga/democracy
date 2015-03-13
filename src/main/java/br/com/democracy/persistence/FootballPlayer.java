package br.com.democracy.persistence;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "FOOOTBALL_PLAYER")
public class FootballPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME", length = 300, nullable = false)
	private String name;
	
	@Column(name = "HEIGHT", precision = 3, scale = 2, nullable = false)
	private BigDecimal height;
	
	@Column(name = "AGE", nullable = false)
	private Integer age;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_GAME_PLAYED")
	private Date lastGamePlayed;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "TEAM_ID")
	private Team team;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public Integer getAge() {
		return age;
	}

	public Date getLastGamePlayed() {
		return lastGamePlayed;
	}

	public Team getTeam() {
		return team;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setLastGamePlayed(Date lastGamePlayed) {
		this.lastGamePlayed = lastGamePlayed;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
