package com.ecom.service.impl;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Cart;
import com.ecom.entities.CartItem;
import com.ecom.entities.Product;
import com.ecom.entities.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.repo.CartRepository;
import com.ecom.repo.ProductRepository;
import com.ecom.repo.UserRepository;
import com.ecom.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public CartDto addItem(ItemRequest item, String userName) {

		int productId = item.getProductId();
		int quantity = item.getQuantity();

		if (quantity <= 0) {
			throw new ResourceNotFoundException("Invalid Quantity !!");			
		}

		// get the user
		User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException());

		// get the product from db: productRepository
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found !!"));
		if (!product.isStock()) {
			throw new ResourceNotFoundException("Product is out of stock");
		}
		System.out.println(product.getProductPrice());

		// create new cartItem: with product and quantity

		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		cartItem.setTotalProductPrice();

		// getting cart from user
		Cart cart = user.getCart();

		// if cart is null that means user does not have any cart
		if (cart == null) {
			// we will create new cart
			cart = new Cart();
			cart.setUser(user);

		}

		// add items mein cart ko

		Set<CartItem> items = cart.getItems();

		AtomicReference<Boolean> flag = new AtomicReference<>(false);

		Set<CartItem> newItems = items.stream().map((i) -> {
			// changes
			if (i.getProduct().getProductId() == product.getProductId()) {

				i.setQuantity(quantity);
				i.setTotalProductPrice();
				flag.set(true);
			}
			return i;
		}).collect(Collectors.toSet());

		// check
		if (flag.get()) {
			// newItems ko items ki jagah set karenge
			items.clear();
			items.addAll(newItems);
		} else {
			cartItem.setCart(cart);
			items.add(cartItem);
		}

		Cart updatedCart = this.cartRepository.save(cart);

		return this.modelMapper.map(updatedCart, CartDto.class);
	}

	@Override
	public CartDto get(String userName) {
		
		User user = this.userRepository.findByEmail(userName)
		.orElseThrow(()-> new ResourceNotFoundException("user not found with given user name !!"));
		
		Cart cart = this.cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("cart is not found !!"));
		
		
		return this.modelMapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto removeItem(String userName, int productId) {
		
		User user = this.userRepository.findByEmail(userName)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with given user name !!"));
				
		Cart cart = user.getCart();
		Set<CartItem> items = cart.getItems();
		
		boolean result = items.removeIf((item)-> item.getProduct().getProductId() == productId);
		System.out.println(result);
		Cart updatedcart = cartRepository.save(cart);
		return this.modelMapper.map(updatedcart, CartDto.class);
	}
	

}
