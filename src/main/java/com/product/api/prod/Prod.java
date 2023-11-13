package com.product.api.prod;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.product.api.cat.Cat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.product.api.alle.Alle;

@Entity
@Table(name = "prod")
public class Prod {

	@Column(name = "prod_id")
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long prodId;
	@Column(name = "prod_name_en")
	private String prodNameEn;
	@Column(name = "prod_name_es")
	private String prodNameEs;
	@Column(name = "prod_desc_en")
	private String prodDescEn;
	@Column(name = "prod_desc_es")
	private String prodDescEs;
	@Column(name = "prod_price")
	private Float prodPrice;
	@ManyToOne
	@JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
	@JsonIgnoreProperties("products")
	private Cat cat;
	@ManyToMany
	@JoinTable(name = "prodalle", joinColumns = @JoinColumn(name = "prod_id"), inverseJoinColumns = @JoinColumn(name = "alle_id"))
	@JsonIgnoreProperties("products")
	List<Alle> allergens;

	public Prod() {
	}

	public Prod(Long prodId, String prodNameEn, String prodNameEs, String prodDescEn, String prodDescEs,
			float prodPrice, Cat cat) {
		super();
		this.prodId = prodId;
		this.prodNameEn = prodNameEn;
		this.prodNameEs = prodNameEs;
		this.prodDescEn = prodDescEn;
		this.prodDescEs = prodDescEs;
		this.prodPrice = prodPrice;
		this.cat = cat;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdNameEn() {
		return prodNameEn;
	}

	public void setProdNameEn(String prodNameEn) {
		this.prodNameEn = prodNameEn;
	}

	public String getProdNameEs() {
		return prodNameEs;
	}

	public void setProdNameEs(String prodNameEs) {
		this.prodNameEs = prodNameEs;
	}

	public String getProdDescEn() {
		return prodDescEn;
	}

	public void setProdDescEn(String prodDescEn) {
		this.prodDescEn = prodDescEn;
	}

	public String getProdDescEs() {
		return prodDescEs;
	}

	public void setProdDescEs(String prodDescEs) {
		this.prodDescEs = prodDescEs;
	}

	public Float getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Float prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Prod [prodId=" + prodId + ", prodNameEn=" + prodNameEn + ", prodNameEs=" + prodNameEs + ", prodDescEn="
				+ prodDescEn + ", prodDescEs=" + prodDescEs + ", prodPrice=" + prodPrice + ", cat=" + cat + "]";
	}

}
