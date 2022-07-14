package com.ecom.payload;

import java.util.Date;

public class CartDto {

	
   private int cartId;
	
	private String items;
	private String title;
	private Date createdDate;
	
	public CartDto(int cartId, String items, String title, Date createdDate) {
		super();
		this.cartId = cartId;
		this.items = items;
		this.title = title;
		this.createdDate = createdDate;
	}
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	} 
	
	
}
