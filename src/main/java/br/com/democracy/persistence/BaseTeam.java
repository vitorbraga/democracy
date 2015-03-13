package br.com.democracy.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "BASE_TEAM")
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseTeam extends Team {
	
	private static final long serialVersionUID = 336891929255120047L;
	
	@Column(name = "NAME_SUFFIX", length = 50, nullable = false)
	private String nameSuffix;

}
