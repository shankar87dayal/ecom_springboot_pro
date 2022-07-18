package com.ecom.service;

import java.util.List;

import com.ecom.payload.CartItemDto;

public interface CartItemService {

	// create
	public CartItemDto createCartItem(CartItemDto cartItemDto, int cartId, int productId);

	// getall

	List<CartItemDto> getAllCartItem();

	// getsingle

	public CartItemDto getCartItem(int cardItemid);

	// update

	public CartItemDto updateCartItem(CartItemDto cd, int cardItemid);

	// delete

	public void deletecartItem(int cardItemid);

}
