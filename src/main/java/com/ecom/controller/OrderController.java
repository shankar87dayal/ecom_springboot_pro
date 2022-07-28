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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.AppConstants;
import com.ecom.payload.ApiResonse;
import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;
import com.ecom.payload.OrderResponse;
import com.ecom.service.OrderService;

@RestController
@RequestMapping("/")
public class OrderController {
	
	//after authentication the user is dynamic
		String userName = "raushan@gmail.com"; 
		
		@Autowired
		private OrderService orderService;
		
		//create 
		@PostMapping("/orders")
		public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request) {
			OrderDto createOrder = this.orderService.createOrder(request, userName);
			
			return new ResponseEntity<OrderDto>(createOrder, HttpStatus.CREATED);
		}

		//getall
		@GetMapping("/orders")
		public ResponseEntity<OrderResponse> get(
				@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
				@RequestParam(value = "pageSize", defaultValue =AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
				@RequestParam(value = "soryby", defaultValue ="orderId",required = false) String sortby,
				@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING,required = false)String sortDir
				
				){
			OrderResponse all = this.orderService.getAll(pageNumber, pageSize, sortby, sortDir);
			return new ResponseEntity<OrderResponse>(all,HttpStatus.OK);
		}
		
		//get single 
		@GetMapping("/orders/{orderId}")
		public ResponseEntity<OrderDto> get(@PathVariable int orderId)
		{
			OrderDto orderDto = this.orderService.get(orderId);
			return new ResponseEntity<OrderDto>(orderDto,HttpStatus.OK);
		}
		
		@DeleteMapping("/orders/{orderId}")
		public ResponseEntity<ApiResonse> delete(@PathVariable int orderId)
		{
			this.orderService.deleteOrder(orderId);
			return new ResponseEntity<ApiResonse>(new ApiResonse("order is deleted successfully !!", true),HttpStatus.OK);
		}
		
		//update order
		@PutMapping("/orders/{orderId}")
		public ResponseEntity<OrderDto> update(@PathVariable int orderId, @RequestBody OrderDto orderDto)
		{
			orderDto.setOrderDelivered(new Date());
			
			OrderDto updateOrder = this.orderService.updateOrder(orderDto, orderId);
			
			return new ResponseEntity<OrderDto>(updateOrder,HttpStatus.OK);
		}
}






















