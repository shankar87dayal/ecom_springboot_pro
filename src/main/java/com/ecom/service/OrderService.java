package com.ecom.service;

import java.util.List;

import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;
import com.ecom.payload.OrderResponse;

public interface OrderService {
	
//create order
	OrderDto createOrder(OrderRequest request, String username);
	
	//update order
	OrderDto updateOrder(OrderDto orderDto, int orderId);
	
	//delete order
	
	void deleteOrder(int orderId);
	
	//get all order
   OrderResponse getAll(int pageNumber, int pageSize, String sortby, String sortDir);
	
	//get single order
	
	OrderDto get(int orderId);
}
