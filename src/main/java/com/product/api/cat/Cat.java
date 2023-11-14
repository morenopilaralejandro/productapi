package com.product.api.cat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.product.api.prod.Prod;

@Entity
@Table(name = "cat")
public class Cat {

	@Column(name = "cat_id")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long catId;
	@Column(name = "cat_name_en")
	private String catNameEn;
	@Column(name = "cat_name_es")
	private String catNameEs;
	@JsonIgnoreProperties("cat")
	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Prod> products;

	public Cat() {
	}

	public Cat(Long catId, String catNameEn, String catNameEs) {
		super();
		this.catId = catId;
		this.catNameEn = catNameEn;
		this.catNameEs = catNameEs;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public String getCatNameEn() {
		return catNameEn;
	}

	public void setCatNameEn(String catNameEn) {
		this.catNameEn = catNameEn;
	}

	public String getCatNameEs() {
		return catNameEs;
	}

	public void setCatNameEs(String catNameEs) {
		this.catNameEs = catNameEs;
	}

	public List<Prod> getProducts() {
		return products;
	}

	public void setProduts(List<Prod> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cat [catId=" + catId + ", catNameEn=" + catNameEn + ", catNameEs=" + catNameEs + ", produts=" + products
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(catId, catNameEn, catNameEs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		return Objects.equals(catId, other.catId) && Objects.equals(catNameEn, other.catNameEn)
				&& Objects.equals(catNameEs, other.catNameEs);
	}

}
