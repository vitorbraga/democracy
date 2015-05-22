package br.com.democracy.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "USER_DISCURSIVE_ANSWER")
@AssociationOverrides({
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "userId")),
		@AssociationOverride(name = "pk.discursiveAnswer", joinColumns = @JoinColumn(name = "discursiveAnswerId")) })
public class UserDiscursiveAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserDiscursiveAnswerId pk = new UserDiscursiveAnswerId();

	@Column(nullable = true, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@Column(nullable = true, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public UserDiscursiveAnswerId getPk() {
		return pk;
	}

	public void setPk(UserDiscursiveAnswerId pk) {
		this.pk = pk;
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	@Transient
	public DiscursiveAnswer getDiscursiveAnswer() {
		return getPk().getDiscursiveAnswer();
	}

	public void setDiscursiveAnswer(DiscursiveAnswer discursiveAnswer) {
		getPk().setDiscursiveAnswer(discursiveAnswer);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserDiscursiveAnswer that = (UserDiscursiveAnswer) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
