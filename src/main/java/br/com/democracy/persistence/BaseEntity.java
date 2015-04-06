package br.com.democracy.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * The Class BaseEntity. This class provides common fields in all classes.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable,
		Comparable<BaseEntity> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8061950789242933329L;

	/** The id. Its the PK of the Entity. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** The registration date. */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGDATE")
	private Date regDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED")
	private Date updated;
	
	/** The registration date. */
	@NotNull
	@Column(name = "REGUSR")
	private String regUser;

	/**
	 * Sets the reg date.
	 * 
	 * @param regDate
	 *            the new reg date
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * Gets the reg date.
	 * 
	 * @return the reg date
	 */
	public Date getRegDate() {
		return regDate;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * Overrides the default equals(Object obj) method. If the parameter obj is
	 * from the same type of the object, returns true if their id value is the
	 * same, otherwise returns false.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().equals(obj.getClass()) && this.getId() != null) {
			return this.getId().equals(((BaseEntity) obj).getId());
		}

		return super.equals(obj);
	}

	/**
	 * Overrides the compareTo method, used in comparable collections. Only
	 * compares if both objects are not null, as well as their IDs.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @throws ClassCastException
	 *             when the entities are not of the same class
	 * @throws NullPointerException
	 *             when the other object or any of the entity's id is null
	 */
	@Override
	public int compareTo(BaseEntity o) {
		if (!this.getClass().equals(o.getClass())) {
			throw new ClassCastException();
		}
		if (this.getId() == null || o == null || o.getId() == null) {
			throw new NullPointerException();
		}
		return Long.signum(this.getId() - o.getId());
	}

}
