package br.com.democracy.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM")
@Inheritance(strategy = InheritanceType.JOINED)
public class Team extends BaseEntity {
	
	private static final long serialVersionUID = -5393347434212764723L;
	
	@Column(name= "NAME", length = 200, nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	private List<FootballPlayer> players;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	private List<SponsorTeam> sponsorTeams;
	
	@Column(name = "BADGE")
	private String badge;

	public String getName() {
		return name;
	}

	public List<FootballPlayer> getPlayers() {
		return players;
	}

	public List<SponsorTeam> getSponsorTeams() {
		return sponsorTeams;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayers(List<FootballPlayer> players) {
		this.players = players;
	}

	public void setSponsorTeams(List<SponsorTeam> sponsorTeams) {
		this.sponsorTeams = sponsorTeams;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}
	
}
