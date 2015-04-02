package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum UserStatusEnum {

	ACTIVE(1),
	
	INACTIVE(2);

	private Integer id;

	private static final Map<Integer, UserStatusEnum> lookup = new HashMap<Integer, UserStatusEnum>();

	static {
		for (UserStatusEnum s : EnumSet.allOf(UserStatusEnum.class)) {
			lookup.put(s.id(), s);
		}
	}

	private UserStatusEnum(Integer id) {
		this.id = id;
	}

	public Integer id() {
		return this.id;
	}

	public static UserStatusEnum get(final Integer id) {
		return lookup.get(id);
	}

	public static UserStatusEnum[] getAllStatus() {
		return UserStatusEnum.values();
	}

}
