package com.ecom.payload;



public class CartItemDto {

	private int cardItemid;

	private ProductDto product;

	private int quantity;

	private double totalProductPrice;
	
	private CartDto cart;

	public int getCardItemid() {
		return cardItemid;
	}

	public void setCardItemid(int cardItemid) {
		this.cardItemid = cardItemid;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}


	
	
	
}
