package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum GenderTypeEnum {

	MALE(1),
	
	FEMALE(2);

	private Integer id;

	private static final Map<Integer, GenderTypeEnum> lookup = new HashMap<Integer, GenderTypeEnum>();

	static {
		for (GenderTypeEnum s : EnumSet.allOf(GenderTypeEnum.class)) {
			lookup.put(s.id(), s);
		}
	}

	private GenderTypeEnum(Integer id) {
		this.id = id;
	}

	public Integer id() {
		return this.id;
	}

	public static GenderTypeEnum get(final Integer id) {
		return lookup.get(id);
	}

	public static GenderTypeEnum[] getAllStatus() {
		return GenderTypeEnum.values();
	}

}
