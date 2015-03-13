package br.com.democracy.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.FootballPlayerDAO;
import br.com.democracy.dto.FootballPlayerInputDTO;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.helper.ConvertHelper;
import br.com.democracy.helper.ValidationHelper;
import br.com.democracy.persistence.FootballPlayer;

@Repository
public class FootballPlayerDAOImpl extends GenericDAOImpl<FootballPlayer> implements FootballPlayerDAO {

	@Override
	public List<FootballPlayer> getFootballPlayers(
			FootballPlayerInputDTO playerDTO) throws ValidationException {
		// criar o criteria
		Criteria criteria = buildQuery();

		// criar alias
		criteria.createAlias("team", "team", Criteria.LEFT_JOIN);

		// adicionar as restricoes
		if (!ValidationHelper.isEmptyOrVoid(playerDTO.getName())) {
			criteria.add(Restrictions.ilike("name", playerDTO.getName(),
					MatchMode.ANYWHERE));
		}

		if (!ValidationHelper.isEmptyOrVoid(playerDTO.getHeight())) {
			criteria.add(Restrictions.le("height",
					new BigDecimal(playerDTO.getHeight())));
		}

		if (!ValidationHelper.isEmptyOrVoid(playerDTO.getAge())) {
			criteria.add(Restrictions.eq("age",
					Integer.valueOf(playerDTO.getAge())));
		}

		if (!ValidationHelper.isEmptyOrVoid(playerDTO.getLastGamePlayed())) {
			criteria.add(Restrictions.ge("lastGamePlayed",
					ConvertHelper.parseDateShort(playerDTO.getLastGamePlayed())));
		}

		if (!ValidationHelper.isEmptyOrVoid(playerDTO.getTeamName())) {
			criteria.add(Restrictions.ilike("team.name",
					playerDTO.getTeamName(), MatchMode.ANYWHERE));
		}

		ScrollableResults scroll = criteria.scroll();
		int maxSize = 2;

		List<FootballPlayer> returnList = new ArrayList<FootballPlayer>();
		if (scroll.first()) {
			int count = 0;

			do {
				returnList.add((FootballPlayer) scroll.get(0));
				count++;
			} while (count < maxSize && scroll.next());

		}

		// retornar os resultados
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllPlayers() {
		// criar o criteria
		Criteria criteria = buildQuery();

		// adicionar o alias
		criteria.createAlias("team", "team");

		// setar as projecoes
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("id")); //[0]
		projectionList.add(Projections.property("name")); //[1]
		projectionList.add(Projections.property("team.name")); //[2]

		criteria.setProjection(projectionList);
		
		criteria.addOrder(Order.asc("name"));

		// retornar a lista
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllPlayersSummary() {
		// criar o criteria
		Criteria criteria = buildQuery();

		// adicionar o alias
		criteria.createAlias("team", "team");

		// setar as projecoes
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("team.id")); // [0]
		projectionList.add(Projections.groupProperty("team.name")); // [1]
		projectionList.add(Projections.rowCount()); // [2]
		projectionList.add(Projections.avg("age")); // [3]
		projectionList.add(Projections.avg("height")); // [4]

		criteria.setProjection(projectionList);
		
		criteria.addOrder(Order.asc("team.name"));

		// retornar a lista
		return criteria.list();
	}

	@Override
	protected Class<FootballPlayer> getClazz() {
		return FootballPlayer.class;
	}

	@Override
	protected void addPropertiedToCriteria(Criteria c, FootballPlayer example) {
	}
	
}
