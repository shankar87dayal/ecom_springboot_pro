package com.ecom.service;

import java.util.List;

import com.ecom.payload.CartDto;

public interface CartService {
	
	//create
	CartDto create(CartDto cartDto); 
	
	//update
	
	CartDto update(CartDto cartDto, int cId); 
	
	//getAll
	
	List<CartDto> get();
	
	//get single
	
	CartDto get(int cId); 
	
	//delete

	void delete(int cId);
}
