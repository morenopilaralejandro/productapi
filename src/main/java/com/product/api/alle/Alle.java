package com.product.api.alle;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.product.api.prod.Prod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alle")
public class Alle {

	@Column(name = "alle_id")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long alleId;
	@Column(name = "alle_name_en")
	private String alleNameEn;
	@Column(name = "alle_name_es")
	private String alleNameEs;
	@ManyToMany(mappedBy = "allergens")
	@JsonIgnoreProperties("products")
	List<Prod> allergens;

	public Alle() {
	}

	public Alle(Long alleId, String alleNameEn, String alleNameEs) {
		super();
		this.alleId = alleId;
		this.alleNameEn = alleNameEn;
		this.alleNameEs = alleNameEs;
	}

	public Long getAlleId() {
		return alleId;
	}

	public void setAlleId(Long alleId) {
		this.alleId = alleId;
	}

	public String getAlleNameEn() {
		return alleNameEn;
	}

	public void setAlleNameEn(String alleNameEn) {
		this.alleNameEn = alleNameEn;
	}

	public String getAlleNameEs() {
		return alleNameEs;
	}

	public void setAlleNameEs(String alleNameEs) {
		this.alleNameEs = alleNameEs;
	}

	@Override
	public String toString() {
		return "Alle [alleId=" + alleId + ", alleNameEn=" + alleNameEn + ", alleNameEs=" + alleNameEs + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(alleId, alleNameEn, alleNameEs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alle other = (Alle) obj;
		return Objects.equals(alleId, other.alleId) && Objects.equals(alleNameEn, other.alleNameEn)
				&& Objects.equals(alleNameEs, other.alleNameEs);
	}

}
