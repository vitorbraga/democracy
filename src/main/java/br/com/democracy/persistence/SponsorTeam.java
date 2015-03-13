package br.com.democracy.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SPONSOR_TEAM")
public class SponsorTeam extends BaseEntity {
	
	private static final long serialVersionUID = -8693248831046980347L;
	
	@Column(name = "INVESTMENT", nullable = false, precision = 10, scale = 2)
	private BigDecimal investment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "SPONSOR_ID")
	private Sponsor sponsor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "TEAM_ID")
	private Team team;

}
