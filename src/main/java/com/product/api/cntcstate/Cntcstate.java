package com.product.api.cntcstate;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.product.api.contact.Contact;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cntcstate")
public class Cntcstate {

	@Column(name = "cntcstate_id")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long cntcstateId;
	@Column(name = "cntcstate_name")
	private String cntcstateName;
	@JsonIgnoreProperties("cntcstate")
	@OneToMany(mappedBy = "cntcstate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contact> contactList;

	public Cntcstate() {
	}

	public Cntcstate(Long cntcstateId, String cntcstateName) {
		super();
		this.cntcstateId = cntcstateId;
		this.cntcstateName = cntcstateName;
	}

	public Long getCntcstateId() {
		return cntcstateId;
	}

	public void setCntcstateId(Long cntcstateId) {
		this.cntcstateId = cntcstateId;
	}

	public String getCntcstateName() {
		return cntcstateName;
	}

	public void setCntcstateName(String cntcstateName) {
		this.cntcstateName = cntcstateName;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	@Override
	public String toString() {
		return "Cntcstate [cntcstateId=" + cntcstateId + ", cntcstateName=" + cntcstateName + ", contactList="
				+ contactList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cntcstateId, cntcstateName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cntcstate other = (Cntcstate) obj;
		return Objects.equals(cntcstateId, other.cntcstateId) && Objects.equals(cntcstateName, other.cntcstateName);
	}

}
