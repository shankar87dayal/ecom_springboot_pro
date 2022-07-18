package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Cart;
import com.ecom.entities.CartItem;
import com.ecom.entities.Category;
import com.ecom.entities.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CartItemDto;
import com.ecom.payload.ProductResponse;
import com.ecom.repo.CartItemRepository;
import com.ecom.repo.CartRepository;
import com.ecom.repo.ProductRepository;
import com.ecom.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CartItemDto createCartItem(CartItemDto cartItemDto, int cartId, int productId) {
		Cart cart = this.cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("not found this cart !!"));
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("not found this product !!"));
		
		CartItem cartItem = this.modelMapper.map(cartItemDto, CartItem.class);
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		CartItem createdcartitem = this.cartItemRepository.save(cartItem);
		return this.modelMapper.map(createdcartitem, CartItemDto.class);
	}

	@Override
	public List<CartItemDto> getAllCartItem() {
		List<CartItem> all = this.cartItemRepository.findAll();
		List<CartItemDto> list = all.stream().map((caritem)-> this.modelMapper.map(caritem, CartItemDto.class)).collect(Collectors.toList());
		return list;
	}
	
	@Override
	public CartItemDto getCartItem(int cardItemid) {
		CartItem cartItem = this.cartItemRepository.findById(cardItemid).orElseThrow(()-> new  ResourceNotFoundException("cartItem with " + cardItemid + "not found"));
		
		return this.modelMapper.map(cartItem, CartItemDto.class);
	}

	
	@Override
	public CartItemDto updateCartItem(CartItemDto cd, int cardItemid) {
		CartItem cartItem = this.cartItemRepository.findById(cardItemid).orElseThrow(()-> new  ResourceNotFoundException("cartItem with " + cardItemid + "not found"));
		cartItem.setQuantity(cd.getQuantity());
		cartItem.setTotalProductPrice(cd.getTotalProductPrice());
		CartItem updated = this.cartItemRepository.save(cartItem);
		return this.modelMapper.map(updated, CartItemDto.class);
	}

	@Override
	public void deletecartItem(int cardItemid) {
		CartItem cartItem = this.cartItemRepository.findById(cardItemid).orElseThrow(()-> new  ResourceNotFoundException("cartItem with " + cardItemid + "not found"));
		this.cartItemRepository.delete(cartItem);

	}

}
