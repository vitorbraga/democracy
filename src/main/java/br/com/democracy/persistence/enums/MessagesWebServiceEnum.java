package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import br.com.democracy.helper.Constants;

public enum MessagesWebServiceEnum {

	REQUEST_OK_ID(0),

	UNKNOW_ERRO_ID(99),

	ERROR_PARSING_FILE(1);

	/** The id. */
	private Long id;

	private static final Map<Long, MessagesWebServiceEnum> lookup = new HashMap<Long, MessagesWebServiceEnum>();

	static {
		for (MessagesWebServiceEnum s : EnumSet
				.allOf(MessagesWebServiceEnum.class))
			lookup.put(s.id(), s);
	}

	/**
	 * Instantiates a new messages webservice enum.
	 * 
	 * @param id
	 *            the id in the database
	 */
	private MessagesWebServiceEnum(final int id) {
		this.id = Long.valueOf(id);
	}

	/**
	 * Id.
	 * 
	 * @return the long
	 */
	public Long id() {
		return this.id;
	}

	public static MessagesWebServiceEnum get(final Long id) {
		return lookup.get(id);
	}

	/**
	 * Overrides the method toString() inherited from Enum so that the returned
	 * value is 'value' instead of the label of the enum.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return Constants.CONCILIATION_CODE + String.format("%03d", this.id);
	}

}
