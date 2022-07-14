package com.ecom.service;

import java.util.List;

import com.ecom.payload.OrderDto;

public interface OrderService {
	
	//create
	OrderDto create(OrderDto orderDto);
	//update
	OrderDto update(OrderDto orderDto,int oId);
	//getall
	List<OrderDto> get();
	//getorder
	OrderDto get(int oId);
	//delete
	void delete(int oId);
	

}
