package br.com.democracy.dao;

import java.util.Date;
import java.util.List;

import br.com.democracy.persistence.HoliDays;

public interface HoliDaysDAO extends GenericDAO<HoliDays> {

	HoliDays getHolyday(Date date);

	List<Object[]> listHoliDays();

}
