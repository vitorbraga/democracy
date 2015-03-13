package br.com.democracy.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.democracy.dao.HoliDaysDAO;
import br.com.democracy.persistence.HoliDays;

public class DateHelper {

	private static HoliDaysDAO holiDayDao;

	private static final BigDecimal MILLISECONDS_IN_ONE_DAY = new BigDecimal(
			86400000L);

	public static Integer dayDifference(Calendar day1, Calendar day2) {

		Long day1Millis = day1.getTimeInMillis();
		Long day2Millis = day2.getTimeInMillis();

		BigDecimal difference = new BigDecimal(day1Millis - day2Millis);
		Integer differenceDays = difference.abs().divide(
				MILLISECONDS_IN_ONE_DAY, RoundingMode.DOWN).intValue();

		return differenceDays;
	}

	public static Date now() {
		Calendar c = Calendar.getInstance();

		return c.getTime();
	}
	
	public static Date tomorrow() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	public static Date startOfDay(Date day) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date endOfDay(Date day) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	public static Date parse(final String date) throws ParseException {
		final SimpleDateFormat parser = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");

		return parser.parse(date);
	}
	

	/**
	 * Format short date to the pattern dd/MM/yyyy.
	 *	Ex: 12/12/2012
	 * @param date the date
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String formatShortDate(final Date date) throws ParseException {
		final SimpleDateFormat parser = new SimpleDateFormat(
				"dd/MM/yyyy");

		return parser.format(date);
	}
	
	/**
	 * Format short date to the pattern dd/MM/yyyy.
	 *	Ex: 12/12/2012
	 * @param date the date
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String formatShorterDate(final Date date) throws ParseException {
		final SimpleDateFormat parser = new SimpleDateFormat(
				"dd/MM/yy");

		return parser.format(date);
	}

	public static Date nextWorkDay(final Date date) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		HoliDays holiday = new HoliDays();

		do {
			c.add(Calendar.DATE, 1);
			holiday = holiDayDao.getHolyday(c.getTime());
		} while (holiday != null
				|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
		
		return c.getTime();
	}

	public static Date addMonths(final Date date, Long monthNumber) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, monthNumber.intValue());
		return c.getTime();
	}
	
	public static Date addDays(final Date date, Long dayNumber) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, dayNumber.intValue());
		return c.getTime();
	}

	public static Date addMinutes(Date date, Integer minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}
	
	public static void setHolidayDAO(HoliDaysDAO holiDaysDAO) {
		DateHelper.holiDayDao = holiDaysDAO;
	}

	

}
