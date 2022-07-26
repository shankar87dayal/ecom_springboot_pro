package com.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	
	//after authentication the user is dynamic
	String userName = "raushan@gmail.com"; 

	@Autowired
	private CartService cartService;
	
	   //create
	
	    @PostMapping("/")
	    public ResponseEntity<CartDto> addItemToCart(@RequestBody ItemRequest request) 
	    {
	    	CartDto cartDto = this.cartService.addItem(request, userName);
	    	return  new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
	    
	    //get cart
	    @GetMapping("/")
	    public ResponseEntity<CartDto> getCart(){
	    	CartDto cartDto = this.cartService.get(userName);
	    	return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
	
	    //remove item from cart
	    @PostMapping("/{productId}")
	    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable int productId)
	    {
	    	CartDto cartDto = this.cartService.removeItem(userName, productId);
	    	return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
		
}







