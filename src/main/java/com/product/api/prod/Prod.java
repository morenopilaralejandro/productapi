package com.product.api.prod;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prod")
public class Prod {

	@Column(name = "prod_id")
	private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long prodId;
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
	@Column(name = "cat_id")
	private Long catId; 
	
	public Prod() {}
	public Prod(Long prodId, String prodNameEn, String prodNameEs, 
			String prodDescEn, String prodDescEs, float prodPrice, 
			Long catId) {
		super();
		this.prodId = prodId;
		this.prodNameEn = prodNameEn;
		this.prodNameEs = prodNameEs;
		this.prodDescEn = prodDescEn;
		this.prodDescEs = prodDescEs;
		this.prodPrice = prodPrice;
		this.catId = catId;
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
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	
	@Override
	public String toString() {
		return "Prod [prodId=" + prodId + ", prodNameEn=" + prodNameEn 
				+ ", prodNameEs=" + prodNameEs + ", prodDescEn=" + prodDescEn 
				+ ", prodDescEs=" + prodDescEs + ", prodPrice=" + prodPrice 
				+ ", catId=" + catId + "]";
	}
}
