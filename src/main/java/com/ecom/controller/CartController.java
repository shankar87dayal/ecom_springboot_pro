package com.ecom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.service.CartService;

import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	
	//after authentication the user is dynamic
//	String userName = "raushan@gmail.com"; 
	
	Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;
	
	   //create
	
	    @PostMapping("/")
	    public ResponseEntity<CartDto> addItemToCart(@RequestBody ItemRequest request,Principal principal) 
	    {
	    	//authentication dynamic
	    	CartDto cartDto = this.cartService.addItem(request, principal.getName());
	    	return  new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
	    
	    //get cart
	    @GetMapping("/")
	    public ResponseEntity<CartDto> getCart(Principal principal){
	    	CartDto cartDto = this.cartService.get(principal.getName());
	    	return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
	
	    //remove item from cart
	    @PutMapping("/{productId}")
	    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable int productId, Principal principal)
	    {
	    	CartDto cartDto = this.cartService.removeItem(principal.getName(), productId);
	    	return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
		
}







