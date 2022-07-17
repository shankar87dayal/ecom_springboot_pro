package com.ecom.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ecom_product")

public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	@Column(name = "product_brand_name",length = 300,unique = true)
	private String productName;
	
	private String productDesc;
	
	private double productPrice;
	
	private boolean stock;
	
	private boolean live;
	
	
	private String imageName;

	//@ManyToOne
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;

	public Product(int productId, String productName, String productDesc, double productPrice, boolean stock,
			boolean live, String imageName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.stock = stock;
		this.live = live;
		this.imageName = imageName;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDesc() {
		return productDesc;
	}


	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public boolean isStock() {
		return stock;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public void setStock(boolean stock) {
		this.stock = stock;
	}


	public boolean isLive() {
		return live;
	}


	public void setLive(boolean live) {
		this.live = live;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
