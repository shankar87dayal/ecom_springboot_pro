package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Order;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.OrderDto;
import com.ecom.repo.OrderRepository;
import com.ecom.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public OrderDto create(OrderDto orderDto) {
		Order order = this.modelMapper.map(orderDto, Order.class);
		Order createdOrd = this.orderRepository.save(order);
		
		return this.modelMapper.map(createdOrd, OrderDto.class);
	}

	@Override
	public OrderDto update(OrderDto orderDto, int oId) {

		Order order = this.orderRepository.findById(oId).orElseThrow(()-> new ResourceNotFoundException("order with " + oId + "not found"));
		order.setOrderStatus(orderDto.getOrderStatus());
		order.setPaymentStatus(orderDto.getPaymentStatus());
		order.setOrderCreated(orderDto.getOrderCreated());
		order.setTotalAmount(orderDto.getTotalAmount());
		order.setBillingAddress(orderDto.getBillingAddress());
		order.setOrderDelivered(orderDto.getOrderDelivered());

		Order updatedOrd = this.orderRepository.save(order);
		return this.modelMapper.map(updatedOrd, OrderDto.class);
	}

	@Override
	public List<OrderDto> get() {
		List<Order> all = this.orderRepository.findAll();
		List<OrderDto> dtos = all.stream().map((ord)-> this.modelMapper.map(ord, OrderDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public OrderDto get(int oId) {
		Order order = this.orderRepository.findById(oId).orElseThrow(()-> new ResourceNotFoundException("order with " + oId + "not found"));
		
		return this.modelMapper.map(order, OrderDto.class);
	}

	@Override
	public void delete(int oId) {
		Order order = this.orderRepository.findById(oId).orElseThrow(()-> new ResourceNotFoundException("order with " + oId + "not found"));

		this.orderRepository.delete(order);
	}

}
