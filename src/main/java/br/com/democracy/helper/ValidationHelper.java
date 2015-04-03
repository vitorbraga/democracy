package br.com.democracy.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.democracy.exception.ValidationException;
import br.com.democracy.messages.Messages;

/**
 * The Class ValidationHelper.
 */
public class ValidationHelper {

	/** The logger. */
	// static Logger logger = Logger.getLogger(ValidationHelper.class);

	private static final String NAME_PERSON = "([0-9A-Za-záéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ&!()%]+ ?)+";

	/** The Constant NAME_COMPANY. */
	private static final String NAME_COMPANY = "([0-9A-Za-záéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ&!()%@$+'.]+ ?)+";

	private static final String QUESTION_OR_ANSWER = "[0-9A-Za-záéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ&!() #%@$+',-.?]+";
	
	/** The Constant DATE. */
	private static final String DATE = "(([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/\\d{4}) \\d{2}:\\d{2}:\\d{2}";

	/** **/
	private static final String DATE_2 = "(([0-2][0-9]|3[0-1])-(0[0-9]|1[0-2])-\\d{4}) \\d{2}:\\d{2}:\\d{2}";

	/** The Constant DATE_SHORT. */
	private static final String DATE_SHORT = "(([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/\\d{4})";

	/** The Constant DATE_PARTIAL. */
	private static final String DATE_PARTIAL = "\\d{2}";

	/** The Constant PHONECODE. */
	private static final String PHONECODE = "\\d{2}";

	/** The Constant PHONE_HOME_NUMBER. */
	private static final String PHONE_HOME_NUMBER = "\\(d{2}\\)(2|3|4|5)\\d{3}\\-d{4}";

	/** The Constant PHONE_CELL_NUMBER. */
	private static final String PHONE_CELL_NUMBER = "\\(d{2}\\)(5|6|7|8|9)\\d{3}\\-d{4}";

	/** The Constant PHONE_CELL_NUMBER. */
	private static final String FULL_PHONE_NUMBER = "\\(\\d{2}\\)\\d{4}\\-\\d{4,5}";

	/** The Constant PHONE_GENERIC_NUMBER. */
	private static final String PHONE_GENERIC_NUMBER = "\\d{8,9}";

	private static final String FULL_PHONE_GENERIC_NUMBER = "\\(\\d{2}\\)\\d{4}\\-\\d{4,5}";

	/** The Constant CREDITCARD. */
	private static final String CREDITCARD = "\\d{19}|\\d{16}|\\d{15}|\\d{14}";

	/** The Constant PERCENTAGE. */
	private static final String PERCENTAGE = "(\\d{1,2}\\.\\d{2}|100\\.0{2})";

	/** The Constant VALUE. */
	private static final String VALUE = "(\\d*\\.\\d{2}|\\d*\\.\\d{1}|\\d+)";

	/** The Constant EMAIL. */
	private static final String EMAIL = "([0-9A-za-z\\.\\_\\-])+@(([a-z]+[\\.])+)([a-z]+)";

	/** The Constant ADDRESS. */
	private static final String ADDRESS = "([0-9A-Za-záéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜàèìòùÀÈÌÒÙªº&!()%;\\-\\.,\\(\\)]+ *)+";

	/** The Constant NUMBER_OR_HYPHEN . */
	private static final String NUMBER_OR_HYPHEN = "(\\d*\\-\\d*)|\\d*";

	/** The Constant ZIPCODE. */
	private static final String ZIPCODE = "\\d{8}";

	/** The Constant ZIPCODE. */
	private static final String MAC_ADDRESS = "([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}";

	/** The Constant NUMBER. */
	private static final String NUMBER = "\\d+";

	private static final String DECIMAL = "\\d+\\.\\d{2}";

	/** The Constant PASSWORD_USER. */
	private static final String PASSWORD_USER = "[a-zA-Z0-9!@#$%&*()_+{}\\[\\]]{7,}";

	/** The Constant LOVCOMBO. */
	private static final String LOVCOMBO = "\\d+(,\\d+)*";

	/** The Constant IDVIEW. */
	private static final String IDVIEW = "[0-9A-Za-z+/*]{6,}";

	/** The Constant LOVCOMBOIDVIEW. */
	private static final String LOVCOMBOIDVIEW = "[0-9A-Za-z+/*]+(,[0-9A-Za-z+/*]+)*";

	private static final String LOVCOMBOMACADDRESS = "[0-9A-Fa-f:]+(,[0-9A-Fa-f:]+)*";

	/** The Constant MIN_PARTIAL_LENGTH. */
	private static final Integer MIN_PARTIAL_LENGTH = 3;

	/** The Constant BETWEENDATE. */
	private static final String DATE_BETWEEN = "([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/\\d{4} 00:00:00;([0-2][0-9]|3[0-1])/(0[0-9]|1[0-2])/\\d{4} (23:59:59|00:00:00)";

	/** The Constant VALUEOPTIONS. */
	private static final String VALUE_OPTIONS = "(L|E|G)";

	/** The Constant AcquirerTecnologyType */
	private static final String ACQ_TECH_TYPE = "(S|O)";

	/** The Constant YES OR NO. */
	private static final String YES_OR_NO = "(Y|N)";

	/** The Constant PASSWORD SYMBOL. */
	private static final String PASS_SYMBOL = "(t|s|c|p|d){8}";

	/** The Constant PHRASE. */
	private static final String PHRASE = "([0-9A-Za-zàáéíóúÁÉÍÓÚâêîôûÀÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ,\\.:;\\?\\(\\)\\{\\}\\[\\]\\\\$\\%!\\+\\=]+ *)+";

	/** The Constant IP. */
	private static final String IP = "(\\d{1,3}\\.){3}\\d{1,3}";

	/** The Constant SITE. */
	private static final String SITE = "((http|https)://)?(www\\.)?(\\w)+(\\.\\w+)+";

	/** The Constant TOKEN_PASS_SYMBOL. */
	private static final String TOKEN_PASS_SYMBOL = "(t|s|c|p|d){4,10}";

	/** The Constant COMMENT. */
	private static final String COMMENT = "([0-9A-Za-zàáéíóúÁÉÍÓÚâêîôûÀÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ\\_\\!\\\\\\;\\'\\?\\.\\-\\n,@#%$\\*\\+=\\(\\)\\[\\]\\{\\}\\/]+ *)+";

	/** The Constant CODE. */
	private static final String CODE = "[0-9A-Za-záéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ\\_]+";

	/** The Constant MINIMUM_MESSAGE_LENGTH. */
	private static final int MINIMUM_MESSAGE_LENGTH = 15;

	/** The Constant PARTOFCELL. */
	private static final String PARTOFCELL = "_*([0-9])+_+([0-9])+_*";

	/** The Constant CREDITCARD_FOUR_DIGITS. */
	private static final String CREDITCARD_FOUR_DIGITS = "\\d{4}";

	/** The Constant INSTALLMENT_MAX_NUMBER. */
	private static final Long INSTALLMENT_MAX_NUMBER = 24L;

	private static final String ACCOUNT_NUMBER = "([[0-9aArRvVxX]*\\-]+ ?)+";

	private static final String ACCOUNT_TYPE = "(P|C)";

	private static final String MERCHANT_TYPE = "(P|C)";

	private static final String ACQUIRER_TECHNOLOGY_TYPE = "(SUB|OP)";

	private static final String MERCHANT_TECHNOLOGY = "(1|2|3)";

	private static final String PLAN_TECHNOLOGY = "(1|2|3)";

	private static final String MERCHANT_OWNERSHIP = "(0|1|2)";

	private static final String MERCHANT_MODEL = "(0|1|2)";

	private static final String PLAN_TYPE = "(0|1)";

	private static final String MERCHANT_OPERATIONAL_SYSTEM = "(0|1|2)";

	private static final String ANTICIPATION_PROFILE = "(T|P|M|L|N)";

	private static final String TRANSACTION_TYPE = "(C|D)";

	private static final String BOOLEAN = "(true|false)";

	private static final String ADDRESS_NUMBER = "([0-9A-Za-z]+ ?)+";

	public static final int COMPUTER_NAME_MAXIMUM_LENGTH = 50;

	public static final String REASON_TYPE = "(A|C|D|E|P|R)";

	/** The Constant CARD_NAME. */
	public static final String CARD_NAME = "[0-9A-Za-záéíóúÁÉÍÓÚâêîôûÂÊÎÔÛãõÃÕçÇäëïöüÄËÏÖÜ'/ ]{3,50}";

	public static final String CARD_EXPIRATION_DATE = "((0[0-9]|1[0-2])/\\d{4})";

	// format 111
	public static final String CARD_SECURITY_CODE_3DIGITS = "^[0-9]{3}$";

	// format 1111
	public static final String CARD_SECURITY_CODE_4DIGITS = "^[0-9]{4}$";

	public static final String ANY_CARD_SECURITY_CODE = "^[0-9]{3,4}$";

	public static final String ANY_CARD_SECURITY_CODE_STATUS = "(U|M)";

	public static final String MASTERCARD_NUMBER = "^5[1-5][0-9]{14}$";

	public static final String VISA_NUMBER = "^4[0-9]{12}(?:[0-9]{3})?$";

	public static final String AMEX_NUMBER = "^3[47][0-9]{13}$";
	
	public static final String HIPERCARD_NUMBER = "(^(3841)([0-9]{15})$)|(^((609820)|(606282))([0-9]{10})$)";

	public static final String DINERS_NUMBER = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$";

	/** The Constant TRANSACTION_CATEGORIES_GROUP. */
	private static final String TRANSACTION_CATEGORIES_GROUP = "([0-9,]+ ?)+";

	private static final String PIPE_LIST = "(\\d+[\\.,]+\\d+)(\\|\\d+[\\.,]+\\d+)*";

	/**
	 * Checks whether a string of digits is a valid credit card number according
	 * to the Luhn algorithm.
	 * 
	 * 1. Starting with the second to last digit and moving left, double the
	 * value of all the alternating digits. For any digits that thus become 10
	 * or more, add their digits together. For example, 1111 becomes 2121, while
	 * 8763 becomes 7733 (from (1+6)7(1+2)3).
	 * 
	 * 2. Add all these digits together. For example, 1111 becomes 2121, then
	 * 2+1+2+1 is 6; while 8763 becomes 7733, then 7+7+3+3 is 20.
	 * 
	 * 3. If the total ends in 0 (put another way, if the total modulus 10 is
	 * 0), then the number is valid according to the Luhn formula, else it is
	 * not valid. So, 1111 is not valid (as shown above, it comes out to 6),
	 * while 8763 is valid (as shown above, it comes out to 20).
	 * 
	 * @param number
	 *            the credit card number to validate.
	 * @return true if the number is valid, false otherwise.
	 */
	public static boolean verifyLuhn(String cardNumber) {
		int sum = 0;

		boolean alternate = false;
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(cardNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}

		return (sum % 10 == 0);
	}

	public static boolean isMCard(String cardNumber) {
		if (isEmptyOrVoid(cardNumber)) {
			return false;
		} else if (cardNumber.matches(MASTERCARD_NUMBER)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isVisa(String cardNumber) {
		if (isEmptyOrVoid(cardNumber)) {
			return false;
		} else if (cardNumber.matches(VISA_NUMBER)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isDiners(String cardNumber) {
		if (isEmptyOrVoid(cardNumber)) {
			return false;
		} else if (cardNumber.matches(DINERS_NUMBER)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isAmex(String cardNumber) {
		if (isEmptyOrVoid(cardNumber)) {
			return false;
		} else if (cardNumber.matches(AMEX_NUMBER)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isAmexSubdivisible(String cardNumber) {
		if (isEmptyOrVoid(cardNumber)) {
			return false;
		} else {
			String bin = cardNumber.substring(0, 6);
			Integer binNbr = ConvertHelper.convertStringToInteger(bin);

			if (((binNbr >= 340000) && (binNbr <= 349999))
					|| ((binNbr >= 370000) && (binNbr <= 375129))
					|| ((binNbr >= 375140) && (binNbr <= 376419))
					|| ((binNbr >= 376430) && (binNbr <= 376439))
					|| ((binNbr >= 376450) && (binNbr <= 376460))
					|| ((binNbr >= 376468) && (binNbr <= 376469))
					|| (binNbr == 376490)
					|| ((binNbr >= 376495) && (binNbr <= 376497))
					|| ((binNbr >= 376499) && (binNbr <= 376519))
					|| ((binNbr >= 376600) && (binNbr <= 376609))
					|| ((binNbr >= 376630) && (binNbr <= 379999))) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static boolean isHipercard(String cardNumber) {
		if (isEmptyOrVoid(cardNumber)) {
			return false;
		} else if (cardNumber.matches(HIPERCARD_NUMBER)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardSecurityCode(String securityCode) {
		if (isEmptyOrVoid(securityCode)) {
			return false;
		} else if (securityCode.matches(ANY_CARD_SECURITY_CODE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardSecurityCodeOrEmpty(String securityCode) {
		if (isEmptyOrVoid(securityCode)) {
			return true;
		} else if (securityCode.matches(ANY_CARD_SECURITY_CODE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardSecurityCode4Digits(String securityCode) {
		if (isEmptyOrVoid(securityCode)) {
			return false;
		} else if (securityCode.matches(CARD_SECURITY_CODE_4DIGITS)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardSecurityCode3Digits(String securityCode) {
		if (isEmptyOrVoid(securityCode)) {
			return false;
		} else if (securityCode.matches(CARD_SECURITY_CODE_3DIGITS)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardSecurityStatusOrEmpty(String securityCodeStatus) {
		if (isEmptyOrVoid(securityCodeStatus)) {
			return true;
		} else if (securityCodeStatus.matches(ANY_CARD_SECURITY_CODE_STATUS)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardExpirationDate(String expirationDate) {
		if (isEmptyOrVoid(expirationDate)) {
			return false;
		} else if (expirationDate.matches(CARD_EXPIRATION_DATE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCardExpirationDateMonth(String expirationMonth) {
		if (isEmptyOrVoid(expirationMonth)) {
			return false;
		} else if (expirationMonth.matches(NUMBER)) {
			if (Integer.valueOf(expirationMonth) > 0
					&& Integer.valueOf(expirationMonth) < 13) {
				return true;
			}
		}
		return false;
	}

	public static boolean isCardExpirationDateYear(String expirationYear) {
		if (isEmptyOrVoid(expirationYear)) {
			return false;
		} else if (expirationYear.matches(NUMBER)) {
			Integer yearNow = Calendar.getInstance().get(Calendar.YEAR);
			if (Integer.valueOf(expirationYear) >= yearNow
					&& Integer.valueOf(expirationYear) <= yearNow + 20) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNameFromCard(String nameFromCard) {
		if (isEmptyOrVoid(nameFromCard)) {
			return false;
		} else if (nameFromCard.matches(CARD_NAME)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isReasonType(String reasonType) {
		if (isEmptyOrVoid(reasonType)) {
			return false;
		} else if (reasonType.matches(REASON_TYPE)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isComputerName(String computerName) {
		if (isEmptyOrVoid(computerName)) {
			return false;
		} else if (computerName.length() > COMPUTER_NAME_MAXIMUM_LENGTH) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isAddressNumber(final String value) {
		if (isEmptyOrVoid(value)) {
			return false;
		} else if (value.matches(ADDRESS_NUMBER)) {
			return true;
		}
		return false;
	}

	public static boolean isAddressNumberOrEmpty(final String value) {
		if (isEmptyOrVoid(value)) {
			return true;
		} else if (value.matches(ADDRESS_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is document.
	 * 
	 * @param document
	 *            the document
	 * @return true, if is document
	 */
	public static boolean isDocument(final String document) {
		if (isEmptyOrVoid(document)) {
			return false;
		} else if (isCpf(document) || isCnpj(document)) {
			return true;
		}
		return false;
	}

	public static boolean isAcquirerTechnologyType(final String value) {
		if (isEmptyOrVoid(value)) {
			return false;
		} else if (value.matches(ACQUIRER_TECHNOLOGY_TYPE)) {
			return true;
		}
		return false;
	}

	public static boolean isAcquirerTechnologyTypeOrEmpty(final String value) {
		if (isEmptyOrVoid(value)) {
			return true;
		} else if (value.matches(ACQUIRER_TECHNOLOGY_TYPE)) {
			return true;
		}
		return false;
	}

	public static boolean isBooleanOrEmpty(final String value) {
		if (isEmptyOrVoid(value)) {
			return true;
		} else if (value.matches(BOOLEAN)) {
			return true;
		}
		return false;
	}

	public static boolean isBoolean(final String value) {
		if (isEmptyOrVoid(value)) {
			return false;
		} else if (value.matches(BOOLEAN)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is document or empty.
	 * 
	 * @param document
	 *            the document
	 * @return true, if is document or empty
	 */
	public static boolean isDocumentOrEmpty(final String document) {
		if (isEmptyOrVoid(document) || isCpf(document) || isCnpj(document)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone home.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone home
	 */
	public static boolean isPhoneHome(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb)) {
			return false;
		} else if (phoneNb.matches(PHONE_HOME_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone home or empty.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone home or empty
	 */
	public static boolean isPhoneHomeOrEmpty(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb) || phoneNb.matches(PHONE_HOME_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone mobile.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone mobile
	 */
	public static boolean isPhoneMobile(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb)) {
			return false;
		} else if (phoneNb.matches(PHONE_CELL_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone mobile or empty.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone mobile or empty
	 */
	public static boolean isPhoneMobileOrEmpty(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb) || phoneNb.matches(PHONE_CELL_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone generic.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone generic
	 */
	public static boolean isPhoneGeneric(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb)) {
			return false;
		} else if (phoneNb.matches(PHONE_GENERIC_NUMBER)) {
			return true;
		}
		return false;
	}

	public static boolean isFullPhoneGeneric(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb)) {
			return false;
		} else if (phoneNb.matches(FULL_PHONE_GENERIC_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone generic or empty.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone generic or empty
	 */
	public static boolean isPhoneGenericOrEmpty(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb) || phoneNb.matches(PHONE_GENERIC_NUMBER)) {
			return true;
		}
		return false;
	}

	public static boolean isFullPhoneGenericOrEmpty(final String phoneNb) {
		if (isEmptyOrVoid(phoneNb)
				|| phoneNb.matches(FULL_PHONE_GENERIC_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is matrix or branch.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is matrix or branch
	 */
	public static boolean isMatrixOrBranch(final String string) {
		if (isEmptyOrVoid(string) || string.matches("(M|B)")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is cpf partial or empty.
	 * 
	 * @param document
	 *            the document
	 * @return true, if is cpf partial or empty
	 */
	public static boolean isCpfPartialOrEmpty(final String document) {
		if (isEmptyOrVoid(document)) {
			return true;
		} else if (isNumber(document)
				&& document.length() >= MIN_PARTIAL_LENGTH
				&& document.length() < 11) {
			return true;
		} else if (document.length() >= 11) {
			return isCpf(document);
		}
		return false;
	}

	/**
	 * Checks if is cnpj.
	 * 
	 * @param cnpj
	 *            the cnpj
	 * @return true, if is cnpj
	 */
	public static boolean isCnpj(final String cnpj) {

		Integer i;
		String digit1;
		String digit2;
		final Long module = Long.valueOf(11);
		Long sum = Long.valueOf(0);
		Long rest;

		if (cnpj.length() != 14 || !isNumber(cnpj)) {
			return false;
		}

		// Calculates first digit
		Long c = Long.valueOf(5);
		for (i = 0; i < 12; i++) {
			if (i.intValue() == 4) {
				c = Long.valueOf(9);
			}
			sum += Long.valueOf(cnpj.charAt(i) - '0') * c;
			c--;
		}
		rest = sum % module;
		if ((rest) < 2) {
			digit1 = "0";
		} else {
			digit1 = String.valueOf(module - rest);
		}

		// Calculates second digit
		sum = Long.valueOf(0);
		c = Long.valueOf(6);
		for (i = 0; i < 13; i++) {
			if (i.intValue() == 5) {
				c = Long.valueOf(9);
			}
			sum += Long.valueOf(cnpj.charAt(i) - '0') * c;
			c--;
		}
		rest = sum % module;
		if ((rest) < 2) {
			digit2 = "0";
		} else {
			digit2 = String.valueOf(module - rest);
		}

		// Verifies digits
		if (cnpj.charAt(12) != digit1.charAt(0)
				|| cnpj.charAt(13) != digit2.charAt(0)) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if is cpf.
	 * 
	 * @param cpf
	 *            the cpf
	 * @return true, if is cpf
	 */
	public static boolean isCpf(final String cpf) {

		Integer i;
		String digit1;
		String digit2;
		final Long module = Long.valueOf(11);
		Long sum = Long.valueOf(0);
		Long rest;

		if (cpf.length() != 11 || !isNumber(cpf)) {
			return false;
		}

		// Calculates first digit
		Long c = Long.valueOf(10);
		for (i = 0; i < 9; i++) {
			sum += Long.valueOf(cpf.charAt(i) - '0') * c;
			c--;
		}
		rest = sum % module;
		if ((rest) < 2) {
			digit1 = "0";
		} else {
			digit1 = String.valueOf(module - rest);
		}

		// Calculates second digit
		sum = Long.valueOf(0);
		c = Long.valueOf(11);
		for (i = 0; i < 10; i++) {
			sum += Long.valueOf(cpf.charAt(i) - '0') * c;
			c--;
		}
		rest = sum % module;
		if ((rest) < 2) {
			digit2 = "0";
		} else {
			digit2 = String.valueOf(module - rest);
		}

		// Verifies digits
		if (cpf.charAt(9) != digit1.charAt(0)
				|| cpf.charAt(10) != digit2.charAt(0)) {
			return false;
		}

		return true;
	}

	public static boolean isCpfOrEmpty(String cpf) {
		if (isEmptyOrVoid(cpf)) {
			return true;
		} else if (isCpf(cpf)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is between date or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is between date or empty
	 */
	public static boolean isDateBetweenOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(DATE_BETWEEN)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is between date.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is between date
	 */
	public static boolean isDateBetween(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(DATE_BETWEEN)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is date.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is date
	 */
	public static boolean isDate(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(DATE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is date with hyphen, instead of slash.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is date
	 */
	public static boolean isDate2(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(DATE_2)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is date short or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is date short or empty
	 */
	public static boolean isDateShortOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(DATE_SHORT)) {
			return true;
		}
		return false;
	}

	public static boolean isDateShort(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(DATE_SHORT)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is date or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is date or empty
	 */
	public static boolean isDateOrEmpty(final String string) {
		if (isEmptyOrVoid(string) || string.matches(DATE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is empty or void.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is empty or void
	 */
	public static boolean isEmptyOrVoid(final String string) {
		if (string == null) {
			return true;
		} else if (string.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is credit card.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is credit card
	 */
	public static boolean isCreditCard(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(CREDITCARD)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is value, empty or void.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is value, empty or void
	 */
	public static boolean isValueOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(VALUE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is percentage or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is percentage or empty
	 */
	public static boolean isPercentageOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(PERCENTAGE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is value.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is value
	 */
	public static boolean isValue(final String string) {
		if (string != null && string.matches(VALUE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is value options or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is value options
	 */
	public static boolean isValueOptionsOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(VALUE_OPTIONS)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is value options.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is value options
	 */
	public static boolean isValueOptions(final String string) {
		if (string != null && string.matches(VALUE_OPTIONS)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is lov combo or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is lov combo or empty
	 */
	public static boolean isLovComboOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(LOVCOMBO)) {
			return true;
		}

		return false;
	}
	
	public static boolean isPipeList(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(PIPE_LIST)) {
			return true;
		}

		return false;
	}
	
	public static boolean isPipeListOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(PIPE_LIST)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is lov combo.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is lov combo
	 */
	public static boolean isLovCombo(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(LOVCOMBO)) {
			return true;
		}

		return false;
	}

	public static boolean isLovComboMacAddress(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(LOVCOMBOMACADDRESS)) {
			return true;
		}

		return false;
	}

	public static boolean isLovComboMacAddressOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(LOVCOMBOMACADDRESS)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is name company or empty.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is name company or empty
	 */
	public static boolean isNameCompanyOrEmpty(String name) {
		if (isEmptyOrVoid(name)) {
			return true;
		} else if (name.matches(NAME_COMPANY)) {
			return true;
		}
		return false;
	}

	public static boolean isSoftDescriptorOrEmpty(String softDescriptor) {
		if (softDescriptor.length() <= 13
				&& isNameCompanyOrEmpty(softDescriptor)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the date is empty or void.
	 * 
	 * @param date
	 *            the date
	 * @return true, if is empty or void
	 */
	public static boolean isEmptyOrVoid(final Date date) {
		if (date == null) {
			return true;
		} else if (date.toString().isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is Y or N.
	 * 
	 * @param expression
	 *            the expression
	 * @return true, if is Y or N
	 */
	public static boolean isYesOrNo(String expression) {
		if (isEmptyOrVoid(expression)) {
			return false;
		} else if (expression.matches(YES_OR_NO)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is Y or N or empty.
	 * 
	 * @param expression
	 *            the expression
	 * @return true, if is Y or N or empty
	 */
	public static boolean isYesOrNoOrEmpty(String expression) {
		if (isEmptyOrVoid(expression) || expression.matches(YES_OR_NO)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is password symbol.
	 * 
	 * @param password
	 *            the password
	 * @return true, if is password symbol
	 */
	public static boolean isPasswordSymbol(String password) {
		if (password.matches(PASS_SYMBOL)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is other deny reason.
	 * 
	 * @param otherDenyReason
	 *            the other deny reason
	 * @return true, if is other deny reason
	 */
	public static boolean isOtherDenyReason(String otherDenyReason) {
		if (isEmptyOrVoid(otherDenyReason)) {
			return true;
		} else if ((otherDenyReason.matches(PHRASE))
				&& (otherDenyReason.length() <= 200)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is name.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is name
	 */
	public static boolean isNameCompany(String name) {
		if (isEmptyOrVoid(name)) {
			return false;
		} else if (name.matches(NAME_COMPANY)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone code.
	 * 
	 * @param phoneCode
	 *            the phone code
	 * @return true, if is phone code
	 */
	public static boolean isPhoneCode(String phoneCode) {
		if (isEmptyOrVoid(phoneCode)) {
			return false;
		} else if (phoneCode.matches(PHONECODE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone partial or empty.
	 * 
	 * @param phoneNb
	 *            the phone nb
	 * @return true, if is phone partial or empty
	 */
	public static boolean isPhonePartialOrEmpty(String phoneNb) {
		if (isEmptyOrVoid(phoneNb)) {
			return true;
		} else if (isNumber(phoneNb) && phoneNb.length() >= MIN_PARTIAL_LENGTH
				&& phoneNb.length() <= 8) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is email.
	 * 
	 * @param email
	 *            the email
	 * @return true, if is email
	 */
	public static boolean isEmail(String email) {
		if (isEmptyOrVoid(email)) {
			return false;
		} else if (email.length() > 200) {
			return false;
		} else if (email.matches(EMAIL)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is email or empty.
	 * 
	 * @param email
	 *            the email
	 * @return true, if is email or empty
	 */
	public static boolean isEmailOrEmpty(String email) {
		if (isEmptyOrVoid(email)) {
			return true;
		} else if (email.length() > 200) {
			return false;
		} else if (email.matches(EMAIL)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is adress.
	 * 
	 * @param address
	 *            the adress
	 * @return true, if is adress
	 */
	public static boolean isAddress(String address) {
		if (isEmptyOrVoid(address)) {
			return false;
		} else if (address.matches(ADDRESS) && address.length() >= 3) {
			return true;
		}
		return false;
	}

	public static boolean isAddressOrEmpty(String address) {
		if (isEmptyOrVoid(address)) {
			return true;
		} else if (address.matches(ADDRESS) && address.length() >= 3) {
			return true;
		}
		return false;
	}

	public static boolean isNumberOrHyphen(String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(NUMBER_OR_HYPHEN)) {
			return true;
		}
		return false;
	}

	public static boolean isNumberOrHyphenOrEmpty(String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(NUMBER_OR_HYPHEN)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is number.
	 * 
	 * @param number
	 *            the number
	 * @return true, if is number
	 */
	public static boolean isNumber(String number) {
		if (isEmptyOrVoid(number)) {
			return false;
		} else if (number.matches(NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is number and if is between 1 and 30.
	 * 
	 * @param number
	 *            the number
	 * @return true, if is number
	 */
	public static boolean isNumberDay(String number) {
		if (isEmptyOrVoid(number)) {
			return false;
		} else if (number.matches(NUMBER)) {
			if (Integer.valueOf(number) > 0 && Integer.valueOf(number) < 31) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNumberDayOrEmpty(String number) {
		if (isEmptyOrVoid(number)) {
			return true;
		} else if (number.matches(NUMBER)) {
			if (Integer.valueOf(number) > 0 && Integer.valueOf(number) < 31) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is number or empty.
	 * 
	 * @param number
	 *            the number
	 * @return true, if is number or empty
	 */
	public static boolean isNumberOrEmpty(String number) {
		if (isEmptyOrVoid(number) || number.matches(NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is adress complement.
	 * 
	 * @param adressComplement
	 *            the adress complement
	 * @return true, if is adress complement
	 */
	public static boolean isAddressComplement(String adressComplement) {
		if (isEmptyOrVoid(adressComplement)) {
			return true;
		} else if (adressComplement.matches(ADDRESS)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is zip code.
	 * 
	 * @param zipCode
	 *            the zip code
	 * @return true, if is zip code
	 */
	public static boolean isZipCode(String zipCode) {
		if (isEmptyOrVoid(zipCode)) {
			return false;
		} else if (zipCode.matches(ZIPCODE)) {
			return true;
		}
		return false;
	}

	public static boolean isZipCodeOrEmpty(String zipCode) {
		if (isEmptyOrVoid(zipCode)) {
			return true;
		} else if (zipCode.matches(ZIPCODE)) {
			return true;
		}
		return false;
	}

	/**
	 * Validate limit and start in search. Both values must be a positive number
	 * 
	 * @param tokenPass
	 *            the token pass
	 * @return start the start row to appear in search (page)
	 */
	public static void validateLimitAndStart(String limit, String start)
			throws ValidationException {
		// validates the limit of the search
		if (ValidationHelper.isNumber(limit)) {
			Integer l = ConvertHelper.convertStringToInteger(limit);
			if (l < 0) {
				throw new ValidationException(Messages.SEARCH_LIMIT_INVALID);
			}
		} else {
			throw new ValidationException(Messages.SEARCH_LIMIT_INVALID);
		}

		// validates the start of the search
		if (ValidationHelper.isNumber(start)) {
			Integer l = ConvertHelper.convertStringToInteger(start);
			if (l < 0) {
				throw new ValidationException(Messages.SEARCH_START_INVALID);
			}
		} else {
			throw new ValidationException(Messages.SEARCH_START_INVALID);
		}
	}

	/**
	 * Checks if is token pass symbol.
	 * 
	 * @param tokenPass
	 *            the token pass
	 * @return true, if is token pass symbol
	 */
	public static boolean isTokenPassSymbol(String tokenPass) {
		if (tokenPass.matches(TOKEN_PASS_SYMBOL)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is a valid comment.
	 * 
	 * @param comment
	 *            the comment
	 * @return true, if is comment
	 */
	public static boolean isComment(String comment) {
		if (isEmptyOrVoid(comment)) {
			return false;
		} else if (comment.matches(COMMENT)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is a valid comment.
	 * 
	 * @param comment
	 *            the comment
	 * @return true, if is comment
	 */
	public static boolean isCommentOrEmpty(String comment) {
		if (isEmptyOrVoid(comment)) {
			return true;
		} else if (comment.matches(COMMENT)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is P (PaymentBrand) or M (Merchant).
	 * 
	 * @param type
	 *            the type
	 * @return true, if is promotion responsible type
	 */
	public static boolean isPromotionResponsible(String type) {
		if (isEmptyOrVoid(type)) {
			return false;
		} else if (("P".equalsIgnoreCase(type)) || ("M".equalsIgnoreCase(type))) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is IP or empty.
	 * 
	 * @param ip
	 *            the ip
	 * @return true, if is IP or empty
	 */
	public static boolean isIPOrEmpty(String ip) {
		if (isEmptyOrVoid(ip)) {
			return true;
		} else if (ip.matches(IP)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is Site.
	 * 
	 * @param site
	 *            the site
	 * @return true, if is Site
	 */
	public static boolean isSite(String site) {
		if (isEmptyOrVoid(site)) {
			return false;
		} else if (site.matches(SITE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is Site or empty.
	 * 
	 * @param site
	 *            the site
	 * @return true, if is Site or empty
	 */
	public static boolean isSiteOrEmpty(String site) {
		if (isEmptyOrVoid(site)) {
			return true;
		} else if (site.matches(SITE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is empty or void.
	 * 
	 * @param list
	 *            the list
	 * @return true, if is empty or void
	 */
	public static boolean isEmptyOrVoid(List<?> list) {
		if (list == null) {
			return true;
		} else if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmptyOrVoid(List<?> list) {
		if (list == null) {
			return false;
		} else if (list.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is merchant or issuing bank.
	 * 
	 * @param whoParcels
	 *            the who parcels
	 * @return true, if is merchant or issuing bank
	 */
	public static boolean isMerchantOrIssuingBank(String whoParcels) {
		if (isEmptyOrVoid(whoParcels)) {
			return false;
		} else if (whoParcels.matches("(M|I)")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is merchant or issuing bank or empty.
	 * 
	 * @param whoParcels
	 *            the who parcels
	 * @return true, if is merchant or issuing bank or empty
	 */
	public static boolean isMerchantOrIssuingBankOrEmpty(String whoParcels) {
		if (isEmptyOrVoid(whoParcels)) {
			return true;
		} else if (whoParcels.matches("(M|I)")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is code.
	 * 
	 * @param code
	 *            the code
	 * @return true, if is code
	 */
	public static boolean isCode(String code) {
		if (isEmptyOrVoid(code)) {
			return false;
		} else if (code.matches(CODE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is code or empty.
	 * 
	 * @param code
	 *            the code
	 * @return true, if is code or empty
	 */
	public static boolean isCodeOrEmpty(String code) {
		if (isEmptyOrVoid(code)) {
			return true;
		} else if (code.matches(CODE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is plan type or empty.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is plan type or empty
	 */
	public static boolean isPlanTypeOrEmpty(String type) {
		if (isEmptyOrVoid(type)) {
			return true;
		} else if (type.matches("(P|C)")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is plan type or empty.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is plan type or empty
	 */
	public static boolean isPlanTypeEnumOrEmpty(String type) {
		if (isEmptyOrVoid(type)) {
			return true;
		} else if (type.matches("(PAGSERVICE|STANDARD)")) {
			return true;
		}
		return false;
	} 
	/**
	 * Checks if is percentage.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is percentage
	 */
	public static boolean isPercentage(String string) {
		if (string != null && string.matches(PERCENTAGE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is other cancel reason.
	 * 
	 * @param reason
	 *            the reason
	 * @return true, if is other cancel reason
	 */
	public static boolean isOtherCancelReason(String reason) {
		if (isEmptyOrVoid(reason)) {
			return true;
		} else if (reason.matches(PHRASE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String string) {
		if (string == null) {
			return false;
		} else if (string.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is phone code or empty.
	 * 
	 * @param phoneCode
	 *            the phone code
	 * @return true, if is phone code
	 */
	public static boolean isPhoneCodeOrEmpty(String phoneCode) {
		if (isEmptyOrVoid(phoneCode) || phoneCode.matches(PHONECODE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is password user.
	 * 
	 * @param pass
	 *            the pass
	 * @return true, if is password user
	 */
	public static boolean isPasswordUser(String pass) {
		if (isEmptyOrVoid(pass)) {
			return false;
		} else if (!pass.matches(PASSWORD_USER)) {
			return false;
		} else if (pass.length() < 7 || pass.length() > 16) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is a id from view.
	 * 
	 * @param id
	 *            the id from view
	 * @return true, if is a valid id
	 */
	public static boolean isIdFromView(final String id) {
		if (isEmptyOrVoid(id)) {
			return false;
		} else if (id.matches(IDVIEW)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is id from view or empty.
	 * 
	 * @param id
	 *            the id
	 * @return true, if is id from view or empty
	 */
	public static boolean isIdFromViewOrEmpty(final String id) {
		if (isEmptyOrVoid(id)) {
			return true;
		} else if (id.matches(IDVIEW)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is lov combo id view.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is lov combo id view
	 */
	public static boolean isLovComboIdView(String string) {

		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(LOVCOMBOIDVIEW)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is lov combo id view or empty.
	 * 
	 * @param string
	 *            the string
	 * @return true, if is lov combo id view or empty
	 */
	public static boolean isLovComboIdViewOrEmpty(String string) {

		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(LOVCOMBOIDVIEW)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is document partial or empty.
	 * 
	 * @param document
	 *            the document
	 * @return true, if is document partial or empty
	 */
	public static boolean isDocumentPartialOrEmpty(String document) {
		if (isCnpjPartialOrEmpty(document) || isCpfPartialOrEmpty(document)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is cnpj partial or empty.
	 * 
	 * @param document
	 *            the document
	 * @return true, if is cnpj partial or empty
	 */
	public static boolean isCnpjPartialOrEmpty(String document) {
		if (isEmptyOrVoid(document)) {
			return true;
		} else if (isNumber(document)
				&& document.length() >= MIN_PARTIAL_LENGTH
				&& document.length() < 14) {
			return true;
		} else if (document.length() >= 14) {
			return isCnpj(document);
		}
		return false;
	}

	/**
	 * Checks if is part of cell.
	 * 
	 * @param cellPhone
	 *            the cell phone
	 * @return true, if is part of cell
	 */
	public static boolean isPartOfCell(String cellPhone) {
		if (isEmptyOrVoid(cellPhone)) {
			return true;
		} else if (cellPhone.matches(PARTOFCELL)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is name person partial or empty.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is name person partial or empty
	 */
	public static boolean isNamePersonPartialOrEmpty(String name) {
		if (isEmptyOrVoid(name)) {
			return true;
		} else if (name.matches(NAME_PERSON)
				&& name.length() >= MIN_PARTIAL_LENGTH) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is name person partial.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is name person partial
	 */
	public static boolean isNamePerson(String name) {
		if (isEmptyOrVoid(name)) {
			return false;
		} else if (name.matches(NAME_PERSON)
				&& name.length() >= MIN_PARTIAL_LENGTH) {
			return true;
		}
		return false;
	}

	public static boolean isNamePersonOrEmpty(String name) {
		if (isEmptyOrVoid(name) || name.matches(NAME_PERSON)) {
			return true;
		}
		return false;
	}

	public static boolean isNamePersonOrCompanyOrEmpty(String name) {
		if (isEmptyOrVoid(name) || name.matches(NAME_PERSON)
				|| name.matches(NAME_COMPANY)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is name company partial or empty.
	 * 
	 * @param name
	 *            the name
	 * @return true, if is name company partial or empty
	 */
	public static boolean isNameCompanyPartialOrEmpty(String name) {
		if (isEmptyOrVoid(name)) {
			return true;
		} else if (name.matches(NAME_COMPANY)
				&& name.length() >= MIN_PARTIAL_LENGTH) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is message subject.
	 * 
	 * @param subject
	 *            the subject
	 * @return true, if is message subject
	 */
	public static boolean isMessageSubject(String subject) {
		if (isEmptyOrVoid(subject)) {
			return false;
		} else if (subject.matches(PHRASE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is code partial or empty.
	 * 
	 * @param code
	 *            the code
	 * @return true, if is code partial or empty
	 */
	public static boolean isCodePartialOrEmpty(String code) {
		if (isEmptyOrVoid(code)) {
			return true;
		} else if (code.matches(CODE) && code.length() >= MIN_PARTIAL_LENGTH) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is message content.
	 * 
	 * @param message
	 *            the message
	 * @return true, if is message content
	 */
	public static boolean isMessageContent(String message) {
		if (isEmptyOrVoid(message)) {
			return false;
		} else if (message.length() >= MINIMUM_MESSAGE_LENGTH
				&& message.matches(PHRASE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is transaction number partial or empty.
	 * 
	 * @param tranNumber
	 *            the tran number
	 * @return true, if is transaction number partial or empty
	 */
	public static boolean isTransactionNumberPartialOrEmpty(String tranNumber) {
		if (isEmptyOrVoid(tranNumber)) {
			return true;
		} else if (isNumber(tranNumber)
				&& tranNumber.length() >= MIN_PARTIAL_LENGTH) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is date partial or empty.
	 * 
	 * @param date
	 *            the date
	 * @return true, if is date partial or empty
	 */
	public static boolean isDatePartialOrEmpty(String date) {
		if (isEmptyOrVoid(date)) {
			return true;
		} else if (date.matches(DATE_PARTIAL)) {
			return true;
		}
		return false;
	}

	/**
	 * Verifies if all the atributes accessibles from get methods of a class are
	 * empty. This method can be used to validate DTOs.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return true, if is all empty
	 */
	public static <T> boolean isAllEmpty(T object) {

		Class<?> c = object.getClass();

		Method[] allMethods = c.getDeclaredMethods();

		for (Method m : allMethods) {
			String mname = m.getName();
			if (!mname.startsWith("get")
					|| (m.getGenericReturnType() == void.class || m
							.getParameterTypes().length > 0)) {
				continue;
			}

			try {
				// executes de method
				Object o = m.invoke(object);

				// if return is null, should continue
				if (o == null) {
					continue;
				}

				// checks if the return is empty
				if (o instanceof String) {
					String s = (String) o;
					if (!ValidationHelper.isEmpty(s)) {
						return false;
					}
				} else if (o instanceof Collection<?>) {
					Collection<?> col = (Collection<?>) o;
					if (col.size() > 0) {
						return false;
					}
				} else if (o.getClass().isArray()) {
					Object[] array = (Object[]) o;
					if (array.length > 0) {
						return false;
					}
				} else {
					return false;
				}

				// Handle any exceptions thrown by method to be invoked.
			} catch (InvocationTargetException e) {
				continue;
			} catch (IllegalAccessException e) {
				continue;
			}
		}

		return true;
	}

	/**
	 * Checks if is partial number or empty.
	 * 
	 * @param number
	 *            the number
	 * @return true, if is number or empty
	 */
	public static boolean isNumberPartialOrEmpty(String number) {
		if (isEmptyOrVoid(number)) {
			return true;
		} else if (number.matches(NUMBER)
				&& number.length() >= MIN_PARTIAL_LENGTH) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is credit card last four digits or empty.
	 * 
	 * @param cardNumber
	 *            the card number
	 * @return true, if is credit card last four digits or empty
	 */
	public static boolean isCreditCardLastFourDigitsOrEmpty(
			final String cardNumber) {

		if (isEmptyOrVoid(cardNumber)
				|| cardNumber.matches(CREDITCARD_FOUR_DIGITS)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is credit card last four digits or empty.
	 * 
	 * @param cardNumber
	 *            the card number
	 * @return true, if is credit card last four digits or empty
	 */
	public static boolean isCreditCardLastFourDigits(final String cardNumber) {

		if (!isEmptyOrVoid(cardNumber)
				&& cardNumber.matches(CREDITCARD_FOUR_DIGITS)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if is installment number or empty.
	 * 
	 * @param number
	 *            the number
	 * @return true, if is installment number or empty
	 */
	public static boolean isInstallmentNumberOrTransactionNumberOrEmpty(
			String number) {
		if (isEmptyOrVoid(number)) {
			return true;
		} else if (number.matches(NUMBER)
				&& Long.valueOf(number).compareTo(INSTALLMENT_MAX_NUMBER) <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantCompanyNameOrDocumentOrEmpty(String string)
			throws ValidationException {
		if (!isNameCompanyOrEmpty(string) && !isNumberOrEmpty(string)) {
			return false;
		}
		return true;
	}

	public static boolean isProcessedByIdOrEmpty(String string)
			throws ValidationException {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.indexOf('_') != -1) {
			String token[] = string.split(",");
			Boolean result = false;
			for (int i = 0; i < token.length; i++) {
				String subString = token[i];
				String acquirerId = subString.split("_")[0];
				String acqTechType = subString.split("_")[1];
				if (isIdFromView(acquirerId) && acqTechType.matches(ACQ_TECH_TYPE)) {
					result= true;
				}
			}
			return result;
		}
		return false;
	}

	public static boolean isProcessedById(String string)
			throws ValidationException {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.indexOf('_') != -1) {
			String acquirerId = string.split("_")[0];
			String acqTechType = string.split("_")[1];
			if (isIdFromView(acquirerId) && acqTechType.matches(ACQ_TECH_TYPE)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isProcessedByIdLovComboOrEmpty(String string)
			throws ValidationException {
		if (isEmptyOrVoid(string)) {
			return true;
		} else {
			String[] idList = string.split(",");

			for (String id : idList) {
				if (!isProcessedById(id)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isAccountNumber(String string)
			throws ValidationException {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (!string.matches(ACCOUNT_NUMBER)) {
			return false;
		} else if (string.length() > 20) {
			return false;
		}
		return true;
	}

	public static boolean isDecimal(String string) throws ValidationException {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (!string.matches(DECIMAL)) {
			return false;
		}
		return true;
	}

	public static boolean isDecimalOrEmpty(String string)
			throws ValidationException {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (!string.matches(DECIMAL)) {
			return false;
		}
		return true;
	}

	public static void validatePasswordUser(String password)
			throws ValidationException {
		// validates the limit of the search
		if (!isPasswordUser(password)) {
			throw new ValidationException(Messages.PASSWORD_INVALID);
		}
	}

	public static boolean isTransactionType(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(TRANSACTION_TYPE)) {
			return true;
		}
		return false;
	}

	public static boolean isAccountType(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(ACCOUNT_TYPE)) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantType(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(MERCHANT_TYPE)) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantTypeOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(MERCHANT_TYPE)) {
			return true;
		}
		return false;
	}

//	public static boolean isMerchantTechnology(final String merchantTechnology) {
//		
//		if (isEmptyOrVoid(merchantTechnology)) {
//			return false;
//		} else {
//			try {
//				MerchantTechnologyEnum.valueOf(merchantTechnology);
//				return true;
//			}
//			catch (Exception e) {
//				return false;
//			}
//		}
//		
//	}

	public static boolean isMerchantTechnologyOrEmpty(final String string) {
		if (isEmptyOrVoid(string) || string.matches(MERCHANT_TECHNOLOGY)) {
			return true;
		}
		return false;
	}

	public static boolean isPlanTechnology(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(PLAN_TECHNOLOGY)) {
			return true;
		}
		return false;
	}

	public static boolean isPlanTechnologyOrEmpty(final String string) {
		if (isEmptyOrVoid(string) || string.matches(PLAN_TECHNOLOGY)) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantOwnership(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(MERCHANT_OWNERSHIP)) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantOwnershipOrEmpty(final String string) {
		if (isEmptyOrVoid(string) || string.matches(MERCHANT_OWNERSHIP)) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantModel(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(MERCHANT_MODEL)) {
			return true;
		}
		return false;
	}

	public static boolean isMerchantModelOrEmpty(final String string) {
		if (isEmptyOrVoid(string) || string.matches(MERCHANT_MODEL)) {
			return true;
		}
		return false;
	}

	public static boolean isPlanType(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(PLAN_TYPE)) {
			return true;
		}
		return false;
	}

//	public static boolean isOwnershipOptionValid(final Long technology,
//			final Long ownership) {
//		if ((technology == MerchantTechnologyEnum.VESPAGUE_PINPAD.id() || technology == MerchantTechnologyEnum.BOTH
//				.id()) && (ownership != 1L && ownership != 2L)) {
//			return false;
//		}
//		return true;
//	}

//	public static boolean isPinPadModelOptionValid(final Long technology,
//			final Long model) {
//		if ((technology == MerchantTechnologyEnum.VESPAGUE_PINPAD.id() || technology == MerchantTechnologyEnum.BOTH
//				.id()) && (model != 1L && model != 2L)) {
//			return false;
//		}
//		return true;
//	}

	public static boolean isOperationalSystem(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(MERCHANT_OPERATIONAL_SYSTEM)) {
			return true;
		}
		return false;
	}

	public static boolean isOperationalSystemOrEmpty(final String string) {
		if (isEmptyOrVoid(string)
				|| string.matches(MERCHANT_OPERATIONAL_SYSTEM)) {
			return true;
		}
		return false;
	}

	public static boolean isAnticipationProfile(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(ANTICIPATION_PROFILE)) {
			return true;
		}
		return false;
	}

	public static boolean isAnticipationProfileOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(ANTICIPATION_PROFILE)) {
			return true;
		}
		return false;
	}
	
	public static boolean isQuestionOrAnswer(final String string) {
		if (isEmptyOrVoid(string)) {
			return false;
		} else if (string.matches(QUESTION_OR_ANSWER)) {
			return true;
		}
		return false;
	}

	public static boolean isQuestionOrAnswerOrEmpty(final String string) {
		if (isEmptyOrVoid(string)) {
			return true;
		} else if (string.matches(QUESTION_OR_ANSWER)) {
			return true;
		}
		return false;
	}
	

	public static void validateUserLogin(String username, String password)
			throws ValidationException {
		if (!isEmail(username)) {
			throw new ValidationException(Messages.USERNAME_INVALID);
		} else if (!isPasswordUser(password)) {
			throw new ValidationException(Messages.PASSWORD_INVALID);
		}
	}

	public static boolean isFullPhoneNumber(String phone) {
		if (isEmptyOrVoid(phone)) {
			return false;
		} else if (!phone.matches(FULL_PHONE_NUMBER)) {
			return false;
		}
		return true;
	}

	public static boolean isFullPhoneNumberOrEmpty(String phone) {
		if (isEmptyOrVoid(phone) || phone.matches(FULL_PHONE_NUMBER)) {
			return true;
		}
		return false;
	}

	public static boolean isPhraseOrEmpty(String message) {
		if (isEmptyOrVoid(message) || message.matches(PHRASE)) {
			return true;
		}
		return false;
	}

	public static boolean isMacAddress(String mac) {
		if (isEmptyOrVoid(mac)) {
			return false;
		} else if (!mac.matches(MAC_ADDRESS)) {
			return false;
		}
		return true;
	}

	/**
	 * Check if the upload file is and Excel Spreedsheet
	 * 
	 * @param file
	 * 
	 * @throws ValidationException
	 */
	public static void isExcelSpreadSheet(UploadedFile file)
			throws ValidationException {
		String fileName = file.getFileName(), contentType = file
				.getContentType();
		if (isEmptyOrVoid(contentType)) {
			throw new ValidationException(Messages.INVALID_FILE_TYPE);
		} else if (contentType.equals("application/vnd.ms-excel")
				|| contentType
						.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
				|| contentType.equals("application/xls")
				|| contentType.equals("application/xlsx")) {
			return;
		} else if (contentType.equals("application/octet-stream")
				|| contentType.equals("application/x-zip-compressed")) {
			String extension = fileName.split("\\.")[fileName.split("\\.").length - 1];
			if (extension.equals("xls") || extension.equals("xlsx")) {
				return;
			}
		}
		throw new ValidationException(Messages.INVALID_FILE_TYPE);
	}

	public static boolean isCategoryGroupOrEmpty(String transactionCategories) {
		if (isEmptyOrVoid(transactionCategories)) {
			return true;
		} else if (transactionCategories.matches(TRANSACTION_CATEGORIES_GROUP)) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyOrVoidArray(Object  array[]) {
		return (array.length==0);
	}

}