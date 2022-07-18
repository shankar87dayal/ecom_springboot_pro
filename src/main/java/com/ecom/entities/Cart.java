package com.ecom.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ecom_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int cartId;
	
	@OneToOne
	private User user;


	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CartItem> item=new HashSet<>();
	
	

	private String items;
	private String title;
	private Date createdDate;
	
	public Cart(int cartId, String items, String title, Date createdDate) {
		super();
		this.cartId = cartId;
		this.items = items;
		this.title = title;
		this.createdDate = createdDate;
	}

	public Cart() {
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
	
	public Set<CartItem> getItem() {
		return item;
	}

	public void setItem(Set<CartItem> item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
