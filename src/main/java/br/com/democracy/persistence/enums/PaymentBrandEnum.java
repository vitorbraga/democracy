package br.com.democracy.persistence.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaymentBrandEnum {

	VISA(1, "VISA"),
	MASTERCARD(2,"Mastercard"),
	SOROCRED(6,"SoroCred"),
	ELO(7,"ELO"),
	DINERS(9,"Diners"),
	AGIPLAN(11,"Agiplan"),
	BANESCARD(15,"Banescard"),
	CABAL(23,"Cabal"),
	CREDSYSTEM(29,"CredSystem"),
	ESPLANADA(35,"Esplanada"),
	CREDZ(64,"CredZ");

	private int id;
	private String description;

	private static final Map<Long, PaymentBrandEnum> lookup = new HashMap<Long, PaymentBrandEnum>();

	static {
		for (PaymentBrandEnum s : EnumSet.allOf(PaymentBrandEnum.class)) {
			lookup.put(s.id(), s);
		}
	}

	private PaymentBrandEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long id() {
		return Long.valueOf(this.id);
	}

	public String description() {
		return this.description;
	}

	public static PaymentBrandEnum get(final Long id) {
		return lookup.get(id);
	}

	public static PaymentBrandEnum[] getAllStatus() {
		return PaymentBrandEnum.values();
	}

	/**
	 * gets a payment brand by its description at EvCash
	 * 
	 * @param paymentBrand
	 * @return
	 */
	public static PaymentBrandEnum get(String paymentBrand) {
		// TODO traduzir evcash pra ca. usado somente no mock
		return VISA;
	}
}
