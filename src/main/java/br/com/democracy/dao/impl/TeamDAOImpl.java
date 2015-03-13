package br.com.democracy.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.TeamDAO;
import br.com.democracy.persistence.Team;

@Repository
public class TeamDAOImpl extends GenericDAOImpl<Team> implements TeamDAO {

	@Override
	protected Class<Team> getClazz() {
		return Team.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, Team example) {
	}
}
