package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Cart;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CartDto;
import com.ecom.repo.CartRepository;
import com.ecom.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CartDto create(CartDto cartDto) {
		Cart cart = this.modelMapper.map(cartDto, Cart.class);
		Cart createdCart = this.cartRepository.save(cart);
		
		return this.modelMapper.map(createdCart, CartDto.class);
	}

	@Override
	public CartDto update(CartDto cartDto, int cId) {
		Cart cart = this.cartRepository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("order with " + cId + "not found"));
		cart.setItems(cartDto.getItems());
		cart.setTitle(cartDto.getTitle());
		cart.setCreatedDate(cartDto.getCreatedDate());
		Cart updatedcart = this.cartRepository.save(cart);
		return this.modelMapper.map(updatedcart, CartDto.class);
	}

	@Override
	public List<CartDto> get() {
		List<Cart> all = this.cartRepository.findAll();
		List<CartDto> list = all.stream().map((cart)-> this.modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public CartDto get(int cId) {
		Cart cart = this.cartRepository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("order with " + cId + "not found"));
		return this.modelMapper.map(cart, CartDto.class);
	}

	@Override
	public void delete(int cId) {
		Cart cart = this.cartRepository.findById(cId).orElseThrow(()-> new ResourceNotFoundException("order with " + cId + "not found"));
		this.cartRepository.delete(cart);
	}

}
