package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PeriodEnum {

	LAST_WEEK(1),
	
	LAST_MONTH(2);

	private Integer id;

	private static final Map<Integer, PeriodEnum> lookup = new HashMap<Integer, PeriodEnum>();

	static {
		for (PeriodEnum s : EnumSet.allOf(PeriodEnum.class)) {
			lookup.put(s.id(), s);
		}
	}

	private PeriodEnum(Integer id) {
		this.id = id;
	}

	public Integer id() {
		return this.id;
	}

	public static PeriodEnum get(final Integer id) {
		return lookup.get(id);
	}

	public static PeriodEnum[] getAllStatus() {
		return PeriodEnum.values();
	}

}
