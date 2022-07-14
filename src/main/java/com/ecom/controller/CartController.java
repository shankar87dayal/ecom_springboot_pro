package com.ecom.controller;

import java.util.Date;
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
import com.ecom.payload.CartDto;
import com.ecom.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

	@Autowired
	private CartService cartService;
	
	   //create
	
	    @PostMapping("/")
	    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) 
	    {
	    	cartDto.setCreatedDate(new Date());
	    	
	    	CartDto createdcart = this.cartService.create(cartDto);
	        return new ResponseEntity<CartDto>(createdcart, HttpStatus.CREATED);
	    }
	
		//update
		@PutMapping("/{cId}")
	    public ResponseEntity<CartDto> updateCart(@RequestBody CartDto cartDto, @PathVariable int cId) 
	    {
	    	CartDto updatedcart = this.cartService.update(cartDto, cId);
	        return new ResponseEntity<CartDto>(updatedcart, HttpStatus.OK);
	    }
	    
		//getAll
		@GetMapping("/")
		public ResponseEntity<List<CartDto>> getCart()
		{
			List<CartDto> list = this.cartService.get();
			return new ResponseEntity<List<CartDto>>(list, HttpStatus.OK);
		}
		
		//get single
		@GetMapping("/{cId}")
		public ResponseEntity<CartDto> getCart( @PathVariable int cId) 
	    {
	    	CartDto cart = this.cartService.get(cId);
	        return new ResponseEntity<CartDto>(cart, HttpStatus.OK);
	    }
		
		//delete
		@DeleteMapping("/{cId}")
		public ResponseEntity<ApiResonse> deleteCart( @PathVariable int cId) 
	    {
	    	this.cartService.delete(cId);
	        return new ResponseEntity<ApiResonse>(new ApiResonse("cart Deleted Successfully", true), HttpStatus.OK);
	    }
		
}







