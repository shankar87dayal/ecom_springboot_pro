package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResonse;
import com.ecom.payload.CartItemDto;
import com.ecom.service.CartItemService;

@RestController
@RequestMapping("/")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	//create
	
	@PostMapping("/products/{productId}/carts/{cId}/cartItem")
	public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto, @PathVariable("cId") int  cartId, @PathVariable("productId") int productId)
	{
		CartItemDto createCartItem = this.cartItemService.createCartItem(cartItemDto, cartId, productId);
		return new ResponseEntity<CartItemDto>(createCartItem, HttpStatus.CREATED);
	}
	

	//getAll
	@GetMapping("/cartItem")
	public ResponseEntity<List<CartItemDto>> get()
	{
		List<CartItemDto> list = this.cartItemService.getAllCartItem();
		return new ResponseEntity<List<CartItemDto>>(list, HttpStatus.OK);
	}
	
	
	//get single
	@GetMapping("/cartItem/{cardItemid}")
	public ResponseEntity<CartItemDto> get(@PathVariable int cardItemid)
	{
		CartItemDto cartItem = this.cartItemService.getCartItem(cardItemid);
		return new ResponseEntity<CartItemDto>(cartItem, HttpStatus.OK);
	}
	
	//update
	@PutMapping("/cartItem/{cardItemid}")
	public ResponseEntity<CartItemDto> update(@RequestBody CartItemDto cartItemDto, @PathVariable int cardItemid)
	{
		CartItemDto updateCartItem = this.cartItemService.updateCartItem(cartItemDto, cardItemid);
		return new ResponseEntity<CartItemDto>(updateCartItem, HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/cartItem/{cardItemid}")
	public ResponseEntity<ApiResonse> delete( @PathVariable int cardItemid)
	{
		this.cartItemService.deletecartItem(cardItemid);
		
		return new ResponseEntity<ApiResonse>(new ApiResonse("cartItem Deleted Successfully", true), HttpStatus.OK);
	}
}
























