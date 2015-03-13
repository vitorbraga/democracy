package br.com.democracy.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "STADIUM")
public class Stadium extends BaseEntity{
	
	private static final long serialVersionUID = -8003523810653374024L;
	
	@Column(name = "NAME", length = 200, nullable = false)
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEAM_ID", nullable = false)
	private Team team;
	
}
