package br.com.democracy.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Os Feriados.
 */
@Entity
@Table(name = "HOLIDAYS")
public class HoliDays extends BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7405318044392742461L;

	/** O vencimento previsto da parcela. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;

	/** O valor da parcela. */
	@Column(name = "DESCRIPTION")
	private String Description;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDescription() {
		return Description;
	}



}
