package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {

	NORMAL(1),

	ADMIN(2);

	private Integer id;

	/** The Constant lookup. */
	private static final Map<Integer, UserTypeEnum> lookup = new HashMap<Integer, UserTypeEnum>();

	static {
		for (UserTypeEnum s : EnumSet.allOf(UserTypeEnum.class))
			lookup.put(s.id(), s);
	}

	private UserTypeEnum(final Integer id) {
		this.id = id;
	}

	public Integer id() {
		return this.id;
	}

	public static UserTypeEnum get(final Integer id) {
		return lookup.get(id);
	}
}
