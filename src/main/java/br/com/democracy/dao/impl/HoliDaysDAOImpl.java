package br.com.democracy.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.democracy.dao.HoliDaysDAO;
import br.com.democracy.helper.DateHelper;
import br.com.democracy.persistence.HoliDays;

@Repository
public class HoliDaysDAOImpl extends GenericDAOImpl<HoliDays> implements
		HoliDaysDAO {

	@Override
	protected void addPropertiedToCriteria(Criteria c, HoliDays example) {
	}

	@Override
	protected Class<HoliDays> getClazz() {
		return HoliDays.class;
	}

	@Override
	public HoliDays getHolyday(Date date) {
		Criteria query = buildQuery();
		query.add(Restrictions.between("date", DateHelper.startOfDay(date),
				DateHelper.endOfDay(date)));
		return (HoliDays) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listHoliDays() {
		// criar SQLQuery
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT * FROM HOLIDAYS");

		return query.list();
	}

}
