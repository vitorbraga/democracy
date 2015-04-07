package br.com.democracy.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import br.com.democracy.exception.ServiceException;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.messages.Messages;
import br.com.democracy.messages.MessagesLog;

public class ConvertHelper {

	/** The logger. */
	private static Logger logger = Logger.getLogger(ConvertHelper.class);

	/** The Constant CONSTANT1. */
	private static final Integer CONSTANT1 = 3;

	/** The Constant CONSTANT2. */
	private static final Integer CONSTANT2 = 1713;

	/** The Constant DATE_FORMAT_NOW. */
	private static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";

	/** The Constant PHONE_CODE. */
	private static final String PHONE_CODE = "\\(\\d{2}\\)";

	/** The Constant PHONE_CODE. */
	private static final String PHONE_NUMBER = "\\d{4}\\-\\d{4}";

	/** The Constant DATE_FORMAT_SQL_TIMESTAMP. */
	private static final String DATE_FORMAT_SQL_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

	/** The Constant DATE_FORMAT_DEFAULT. */
	private static final String DATE_FORMAT_DEFAULT = "dd-MM-yyyy HH:mm:ss";

	/** The Constant DATE_FORMAT_FROM_VIEW. */
	private static final String DATE_FORMAT_FROM_VIEW = "dd/MM/yyyy HH:mm:ss";

	/** The Constant DATE_FORMAT_SHORT. */
	private static final String DATE_FORMAT_SHORT = "dd/MM/yyyy";

	/** The Constant DATE_FORMAT_TIME. */
	private static final String DATE_FORMAT_TIME = "HH:mm:ss";

	/** The Constant DATE_FORMAT_EDI_FILE. */
	private static final String DATE_FORMAT_EDI_FILE = "yyyyMMdd";

	/** The Constant DATE_FORMAT_EDI_FILE_YEAR_SHORT. */
	private static final String DATE_FORMAT_EDI_FILE_YEAR_SHORT = "yyMMdd";

	/** The Constant DATE_FORMAT_TIME_EDI_FILE. */
	private static final String DATE_FORMAT_TIME_EDI_FILE = "hhmmss";

	/** The Constant DATE_FORMAT_PINPAD. */
	private static final String DATE_FORMAT_PINPAD = "ddMMyyyy";

	/** Characters that are not number, A to Z or a to z */
	private static final String SPECIAL_CHARACTERS = "[^0-9^a-z^A-Z]";

	/** The Constant sdf. */
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			DATE_FORMAT_NOW, Locale.US);

	public static Object translate(String plainName) {
		String temp = Normalizer.normalize(plainName,
				java.text.Normalizer.Form.NFD);
		return temp.replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Generate date. Based on actual timestamp this method generate a date n
	 * days after or before with the exactly hour, minute and second specified.
	 * 
	 * @param days
	 *            the days
	 * @param hour
	 *            the hour
	 * @param minute
	 *            the minute
	 * @param second
	 *            the second
	 * @return the date
	 */
	public static Date generateDate(final Integer days, final Integer hour,
			final Integer minute, final Integer second) {
		final Calendar cal = Calendar.getInstance();
		final Long searchDate = cal.getTimeInMillis();
		final SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd 00:00:00.0", Locale.US);
		return java.sql.Timestamp.valueOf(formater.format(fixDateDays(days,
				searchDate)));

	}

	public static Date generateDateMonths(final Integer months,
			final Integer hour, final Integer minute, final Integer second) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, months);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));

		final Long searchDate = cal.getTimeInMillis();
		final String date = sdf.format(searchDate);
		final String day = date.substring(0, 2);
		final String month = date.substring(3, 5);
		final String year = date.substring(6, 10);
		return java.sql.Timestamp.valueOf(year + "-" + month + "-" + day + " "
				+ hour + ":" + minute + ":" + second);

	}

	public static Date generateDateWeeks(final Integer weeks,
			final Integer hour, final Integer minute, final Integer second) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, weeks);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

		final Long searchDate = cal.getTimeInMillis();
		final String date = sdf.format(searchDate);
		final String day = date.substring(0, 2);
		final String month = date.substring(3, 5);
		final String year = date.substring(6, 10);
		return java.sql.Timestamp.valueOf(year + "-" + month + "-" + day + " "
				+ hour + ":" + minute + ":" + second);
	}

	/**
	 * Generate date. Based on current timestamp this method generates a date n
	 * days after or before.
	 * 
	 * @param days
	 *            the days
	 * @return the date
	 */
	public static Date generateDate(final Integer days) {
		/** Obtains current date. */
		final Calendar cal = Calendar.getInstance();

		/** Obtains time in milisseconds from current date. */
		final Long searchDate = cal.getTimeInMillis();

		/**
		 * Forwards/rewinds date as asked by 'days' and formats resulting date
		 * as SQL Timestamp.
		 */
		final SimpleDateFormat mySdf = new SimpleDateFormat(
				DATE_FORMAT_SQL_TIMESTAMP);
		return java.sql.Timestamp.valueOf(mySdf.format(fixDateDays(days,
				searchDate)));
	}

	/**
	 * Fix date using days. Go forward or backward n days on actual date.
	 * 
	 * @param days
	 *            the days
	 * @param searchDate
	 *            the search date
	 * @return the long
	 */
	private static Long fixDateDays(final Integer days, final Long searchDate) {
		return searchDate + (Long.valueOf(86400000) * days);
	}

	/**
	 * Parse the Date From from Dateselect string.
	 * 
	 * @param dateSelect
	 *            the date select
	 * @return the date
	 * @throws ValidationException
	 */
	public static Date parseFromDateSelect(final String dateSelect)
			throws ValidationException {

		try {
			if (ValidationHelper.isEmptyOrVoid(dateSelect)) {
				return null;
			}
			String[] dates = dateSelect.split(";");
			if (dates.length != 2) {
				throw new ValidationException(Messages.DATE_FIELD_INVALID);
			}
			SimpleDateFormat formatFromView = new SimpleDateFormat(
					DATE_FORMAT_FROM_VIEW);
			SimpleDateFormat defaultFormat = new SimpleDateFormat(
					DATE_FORMAT_SQL_TIMESTAMP);
			Date date = formatFromView.parse(dates[0]);
			return java.sql.Timestamp.valueOf(defaultFormat.format(DateHelper
					.startOfDay(date)));
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
	}

	public static Date parseDate(final String date) throws ValidationException {
		SimpleDateFormat formatFromView = new SimpleDateFormat(
				DATE_FORMAT_FROM_VIEW);

		Date dateParsed = null;
		try {
			dateParsed = formatFromView.parse(date);
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
		return dateParsed;
	}

	public static Date parseDateShort(final String date)
			throws ValidationException {
		SimpleDateFormat formatFromView = new SimpleDateFormat(
				DATE_FORMAT_SHORT);

		Date dateParsed = null;
		try {
			dateParsed = formatFromView.parse(date);
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
		return dateParsed;
	}

	public static Date parseDateEdiFile(final String date)
			throws ValidationException {
		SimpleDateFormat formatFromView = new SimpleDateFormat(
				DATE_FORMAT_EDI_FILE);

		Date dateParsed = null;
		try {
			dateParsed = formatFromView.parse(date);
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
		return dateParsed;
	}

	public static Date parseDateEdiFileYearShort(final String date)
			throws ValidationException {
		SimpleDateFormat formatFromView = new SimpleDateFormat(
				DATE_FORMAT_EDI_FILE_YEAR_SHORT);

		Date dateParsed = null;
		try {
			dateParsed = formatFromView.parse(date);
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
		return dateParsed;
	}

	public static Date parseDateTimeEdiFile(final String date)
			throws ValidationException {
		SimpleDateFormat formatFromView = new SimpleDateFormat(
				DATE_FORMAT_TIME_EDI_FILE);

		Date dateParsed = null;
		try {
			dateParsed = formatFromView.parse(date);
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
		return dateParsed;
	}

	/**
	 * Parse the Date To from Dateselect string.
	 * 
	 * @param dateSelect
	 *            the date select
	 * @return the date
	 * @throws ValidationException
	 * @throws ParseException
	 */
	public static Date parseToDateSelect(final String dateSelect)
			throws ValidationException {

		try {
			if (ValidationHelper.isEmptyOrVoid(dateSelect)) {
				return null;
			}
			String[] dates = dateSelect.split(";");
			if (dates.length != 2) {
				throw new ValidationException(Messages.DATE_FIELD_INVALID);
			}
			SimpleDateFormat formatFromView = new SimpleDateFormat(
					DATE_FORMAT_FROM_VIEW);
			SimpleDateFormat defaultFormat = new SimpleDateFormat(
					DATE_FORMAT_SQL_TIMESTAMP);
			Date date = formatFromView.parse(dates[1]);
			return java.sql.Timestamp.valueOf(defaultFormat.format(DateHelper
					.endOfDay(date)));
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}

	}

	/**
	 * Convert string to long. If string is empty or null, return null.
	 * 
	 * @param string
	 *            the string
	 * @return long the long
	 */
	public static Long convertStringToLong(String string) {
		if (!ValidationHelper.isEmptyOrVoid(string)) {
			return Long.valueOf(string);
		}
		return null;
	}

	/**
	 * Convert string to integer. If string is empty or null, return null.
	 * 
	 * @param string
	 *            the string
	 * @return long the long
	 */
	public static Integer convertStringToInteger(String string) {
		if (!ValidationHelper.isEmptyOrVoid(string)) {
			return Integer.valueOf(string);
		}
		return null;
	}

	/**
	 * Convert string to bigDecimal. If string is empty or null, return null.
	 * 
	 * @param string
	 *            the string
	 * @return long the long
	 */
	public static BigDecimal convertStringToBigDecimal(String string) {
		if (!ValidationHelper.isEmptyOrVoid(string)) {
			return new BigDecimal(string);
		}
		return null;
	}

	/**
	 * Convert lov combo to list.
	 * 
	 * @param lovCombo
	 *            the lov combo
	 * @return the list
	 * @throws ValidationException
	 *             the validation exception
	 */
	public static List<Long> convertLovComboToList(final String lovCombo) {

		List<Long> listLong = new ArrayList<Long>();

		if (ValidationHelper.isEmptyOrVoid(lovCombo)) {
			return listLong;
		}

		final String[] list = lovCombo.split(",");
		final Integer size = list.length;
		Integer index;
		for (index = 0; index < size; index++) {
			listLong.add(Long.valueOf(list[index]));
		}

		return listLong;
	}

	public static List<BigDecimal> convertPipeListToBigDecimalList(
			final String lovCombo) {

		List<BigDecimal> listLong = new ArrayList<BigDecimal>();

		if (ValidationHelper.isEmptyOrVoid(lovCombo)) {
			return listLong;
		}

		final String[] list = lovCombo.split("\\|");
		final Integer size = list.length;
		Integer index;
		for (index = 0; index < size; index++) {
			listLong.add(new BigDecimal(list[index]));
		}

		return listLong;
	}

	public static List<String> convertLovComboStringToList(final String lovCombo) {

		List<String> listString = new ArrayList<String>();

		if (ValidationHelper.isEmptyOrVoid(lovCombo)) {
			return listString;
		}

		final String[] list = lovCombo.split(",");
		final Integer size = list.length;
		Integer index;
		for (index = 0; index < size; index++) {
			listString.add(list[index]);
		}

		return listString;
	}

	/**
	 * Convert lov combo Id From View to list.
	 * 
	 * @param lovCombo
	 *            the lov combo Id From View
	 * @return the list
	 * @throws ValidationException
	 * @throws ValidationException
	 *             the validation exception
	 */
	public static List<Long> convertLovComboIdFromViewToList(
			final String lovCombo) throws ValidationException {

		List<Long> listLong = new ArrayList<Long>();

		if (ValidationHelper.isEmptyOrVoid(lovCombo)) {
			return listLong;
		}

		final String[] list = lovCombo.split(",");
		final Integer size = list.length;
		Integer index;
		for (index = 0; index < size; index++) {
			listLong.add(convertIdFromView((list[index])));
		}

		return listLong;
	}

	/**
	 * Convert id to view. Performe some changes to offuscate the id to client.
	 * This method does not perform any check of id is nul or empty.
	 * 
	 * @param id
	 *            the id
	 * @return the string
	 * @throws ServiceException
	 *             the service exception
	 */
	public static String convertIdToView(Long id) throws ServiceException {

		try {

			// changes in the id of long
			id = (id + CONSTANT2) * CONSTANT1;

			// convert into bytes[] to use base64 encode
			byte[] bytesId = id.toString().getBytes("UTF-8");
			String base64Id = Base64Utility.encode(bytesId);

			// replace = char to obfuscate base64 encode
			base64Id = base64Id.replace("==", "*3");
			base64Id = base64Id.replace("=", "*7*");

			return base64Id;

		} catch (UnsupportedEncodingException exception) {
			logger.log(Level.ERROR, MessagesLog.CONVERT_ID_TO_VIEW_ERROR
					+ " id=" + id.toString());
			throw new ServiceException(Messages.ID_INVALID, exception);
		}
	}

	/**
	 * Convert id from view. Always need to call this method after validate the
	 * id from view. This method does not perform check if id is null or empty.
	 * 
	 * @param id
	 *            the id
	 * @return the long
	 * @throws ValidationException
	 *             the validation exception
	 */
	public static Long convertIdFromView(String id) throws ValidationException {

		try {

			// get back = character
			id = id.replace("*3", "==");
			id = id.replace("*7*", "=");

			// decode base64 and transform into string
			byte[] bytesId = Base64Utility.decode(id);
			if (bytesId == null) {
				logger.log(Level.WARN, Messages.ID_INVALID);
				throw new ValidationException(Messages.ID_INVALID);
			}
			final Integer intId = ((int) Integer.valueOf(new String(bytesId,
					"UTF-8")) / CONSTANT1) - CONSTANT2;

			// transform in long and return
			return Long.valueOf(intId);

		} catch (Base64Exception exception) {
			logger.log(Level.WARN, Messages.ID_INVALID);
			throw new ValidationException(Messages.ID_INVALID, exception);
		} catch (NumberFormatException exception) {
			logger.log(Level.WARN, Messages.ID_INVALID);
			throw new ValidationException(Messages.ID_INVALID, exception);
		} catch (UnsupportedEncodingException exception) {
			logger.log(Level.WARN, Messages.ID_INVALID);
			throw new ValidationException(Messages.ID_INVALID, exception);
		}
	}


	/**
	 * Convert Date to view.
	 * 
	 * @param id
	 *            the id
	 * @return String the String in this pattern "dd-MM-yyyy HH:mm:ss"
	 */
	public static String dateToView(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_DEFAULT);
			return mySdf.format(date);
		}

		return null;
	}

	public static String dateToViewShort(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_SHORT);
			return mySdf.format(date);
		}

		return null;
	}

	public static String dateToViewTime(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_TIME);
			return mySdf.format(date);
		}

		return null;
	}

	public static String dateToEdiFile(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_EDI_FILE);
			return mySdf.format(date);
		}

		return null;
	}

	public static String dateToEdiFileYearShort(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_EDI_FILE_YEAR_SHORT);
			return mySdf.format(date);
		}

		return null;
	}

	public static String dateTimeToEdiFile(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_TIME_EDI_FILE);
			return mySdf.format(date);
		}

		return null;
	}

	/**
	 * Convert the date string sent to view to a Date.
	 * 
	 * @param date
	 * @return
	 * @throws ValidationException
	 */
	public static Date parseBackDateToView(final String date)
			throws ValidationException {
		SimpleDateFormat formatFromView = new SimpleDateFormat(
				DATE_FORMAT_DEFAULT);

		Date dateParsed = null;
		try {
			dateParsed = formatFromView.parse(date);
		} catch (ParseException e) {
			throw new ValidationException(Messages.DATE_FIELD_INVALID);
		}
		return dateParsed;
	}

	/**
	 * Validate and convert transaction number
	 * 
	 * @param transactionNumber
	 *            the transaction number
	 * @return the long
	 * @throws ValidationException
	 *             the validation exception
	 */
	public static Long validateAndConvertTransactionNumber(
			String transactionNumber) throws ValidationException {
		if (!ValidationHelper.isNumber(transactionNumber)) {
			throw new ValidationException(Messages.TRANSACTION_NUMBER_INVALID);
		} else {
			return Long.valueOf(transactionNumber);
		}
	}

	// public static List<ProcessedByPairDTO>
	// convertProcessedByLovComboIdFromViewToList(
	// String processor) throws ValidationException {
	// String[] idPairs = processor.split(",");
	// List<ProcessedByPairDTO> convertedList = new
	// ArrayList<ProcessedByPairDTO>();
	//
	// for (String pair : idPairs) {
	// ProcessedByPairDTO convertedPair = convertProcessedById(pair);
	//
	// convertedList.add(convertedPair);
	// }
	//
	// return convertedList;
	// }
	//
	// public static ProcessedByPairDTO convertProcessedById(String pair)
	// throws ValidationException {
	// if (!ValidationHelper.isEmptyOrVoid(pair)) {
	// ProcessedByPairDTO convertedPair = new ProcessedByPairDTO();
	// String[] splittedPair = pair.split("_");
	//
	// convertedPair.setProcessorId(convertIdFromView(splittedPair[0]));
	// convertedPair.setProcessorTechnology(splittedPair[1]);
	//
	// return convertedPair;
	// }
	// return null;
	// }

	public static String getPhoneCode(String phone) {
		String[] phoneCode = phone.split(PHONE_NUMBER);
		return phoneCode[0].replace(")", "").replace("(", "");

	}

	public static String getPhoneNumber(String phone) {
		String[] phoneNumber = phone.split(PHONE_CODE);
		return phoneNumber[1].replaceAll("-", "");

	}

	/**
	 * Convert Date to Pinpad.
	 * 
	 * @param id
	 *            the id
	 * @return String the String in this pattern "ddMMyyyy"
	 */
	public static String dateToPinPad(final Date date) {

		if (!ValidationHelper.isEmptyOrVoid(date)) {
			final SimpleDateFormat mySdf = new SimpleDateFormat(
					DATE_FORMAT_PINPAD);
			return mySdf.format(date);
		}

		return null;
	}

	/**
	 * Removes all characters that are not number, A to Z or a to z of a string
	 * 
	 * @param string
	 * @return
	 */
	public static String removeSpecialCharacters(String string) {
		string = string.replaceAll(SPECIAL_CHARACTERS, "");
		return string;
	}

	public static void main(String[] args) throws ServiceException {
		System.out.println(ConvertHelper.convertIdToView(0L));

	}

	public static List<BigDecimal> roundBigDecimaListToView(
			List<BigDecimal> oldList) {
		List<BigDecimal> newList = new ArrayList<BigDecimal>();
		for (int i = 0; i < oldList.size(); i++) {
			newList.add(oldList.get(i).setScale(2, RoundingMode.HALF_UP));
		}
		return newList;
	}

	public static String getDateFormatTime() {
		return DATE_FORMAT_TIME;
	}

	public static BigDecimal longToBigDecimal(Long longNumber) {
		String aux = longNumber.toString();
		return convertStringToBigDecimal(aux);
	}
	
	public static String bigDecimalToEdiFile(BigDecimal value) {
		return value.multiply(new BigDecimal(100.00)).toBigInteger().toString();
	}
}
