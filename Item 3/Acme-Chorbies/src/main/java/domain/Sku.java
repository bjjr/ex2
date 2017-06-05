
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Sku extends DomainEntity {

	// Attributes  --------------------------------

	private String	label;
	private String	justification;
	private boolean	cancelled;


	@NotBlank
	@NotNull
	public String getLabel() {
		return this.label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	@SafeHtml
	public String getJustification() {
		return this.justification;
	}

	//@NotBlank
	public void setJustification(final String justification) {
		this.justification = justification;
	}

	public boolean getCancelled() {
		return this.cancelled;
	}

	public void setCancelled(final boolean cancelled) {
		this.cancelled = cancelled;
	}


	// Relationships --------------------------------

	private Administrator	administrator;
	private Event			event;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(final Administrator administrator) {
		this.administrator = administrator;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}

}
