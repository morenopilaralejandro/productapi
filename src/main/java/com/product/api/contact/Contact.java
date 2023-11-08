package com.product.api.contact;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.product.api.cntcstate.Cntcstate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

	@Column(name = "contact_id")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long contactId;
	@Column(name = "contact_name")
	private String contactName;
	@Column(name = "contact_email")
	private String contactEmail;
	@Column(name = "contact_msg")
	private String contactMsg;
	@Column(name = "contact_date")
	private LocalDateTime contactDate;
	@ManyToOne
	@JoinColumn(name = "cntcstate_id", referencedColumnName = "cntcstate_id")
	@JsonIgnoreProperties("contactList")
	private Cntcstate cntcstate;

	public Contact() {
	}

	public Contact(Long contactId, String contactName, String contactEmail, String contactMsg,
			LocalDateTime contactDate, Cntcstate cntcstate) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactMsg = contactMsg;
		this.contactDate = contactDate;
		this.cntcstate = cntcstate;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactMsg() {
		return contactMsg;
	}

	public void setContactMsg(String contactMsg) {
		this.contactMsg = contactMsg;
	}

	public LocalDateTime getContactDate() {
		return contactDate;
	}

	public void setContactDate(LocalDateTime contactDate) {
		this.contactDate = contactDate;
	}

	public Cntcstate getCntcstate() {
		return cntcstate;
	}

	public void setCntcstate(Cntcstate cntcstate) {
		this.cntcstate = cntcstate;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", contactName=" + contactName + ", contactEmail=" + contactEmail
				+ ", contactMsg=" + contactMsg + ", contactDate=" + contactDate + ", cntcstate=" + cntcstate + "]";
	}

}
