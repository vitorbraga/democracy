package br.com.democracy.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SPONSOR")
public class Sponsor extends BaseEntity {

	private static final long serialVersionUID = 3260774000214528827L;

	@Column(name = "NAME", length = 200, nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sponsor")
	private List<SponsorTeam> sponsorTeams;

}
