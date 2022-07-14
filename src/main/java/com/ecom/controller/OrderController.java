package com.ecom.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import com.ecom.payload.OrderDto;
import com.ecom.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	
	//create
	
	@PostMapping("/")
	private ResponseEntity<OrderDto> createOrd(@Valid @RequestBody OrderDto orderDto)
	{
		
		orderDto.setOrderCreated(new Date());
		
		OrderDto createOrd = this.orderService.create(orderDto);
		
		return new ResponseEntity<OrderDto>(createOrd, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{oId}")
	private ResponseEntity<OrderDto> createOrd(@Valid @RequestBody OrderDto orderDto, @PathVariable int oId)
	{
		OrderDto updatedOrd = this.orderService.update(orderDto, oId);
		
		return new ResponseEntity<OrderDto>(updatedOrd, HttpStatus.OK);
	}
	
	//getall
	@GetMapping("/")
	private ResponseEntity<List<OrderDto>> getOrd()
	{
		List<OrderDto> list = this.orderService.get();
		return new ResponseEntity<List<OrderDto>>(list, HttpStatus.OK);
	}
	
	//getorder
	@GetMapping("/{oId}")
	private ResponseEntity<OrderDto> getOrd(@PathVariable int oId)
	{
		OrderDto orderDto = this.orderService.get(oId);
		
		return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/{oId}")
	private ResponseEntity<ApiResonse> deleteOrd(@PathVariable int oId)
	{
		this.orderService.delete(oId);
		
		return new ResponseEntity<ApiResonse>(new ApiResonse("order Deleted Successfully", true), HttpStatus.OK);
	}
}














