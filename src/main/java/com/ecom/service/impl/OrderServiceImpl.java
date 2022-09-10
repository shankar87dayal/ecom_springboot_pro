package com.ecom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.entities.Cart;
import com.ecom.entities.CartItem;
import com.ecom.entities.Order;
import com.ecom.entities.OrderItem;
import com.ecom.entities.Product;
import com.ecom.entities.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;
import com.ecom.payload.OrderResponse;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.repo.CartRepository;
import com.ecom.repo.OrderRepository;
import com.ecom.repo.UserRepository;
import com.ecom.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public OrderDto createOrder(OrderRequest request, String username) {
		
		
		User user = this.userRepository.findByEmail(username).orElseThrow(ResourceNotFoundException::new);
		
		int cartId = request.getCartId();
		String address = request.getAddress();
		
		
		Cart cart = this.cartRepository.findById(cartId).orElseThrow(ResourceNotFoundException::new);
		
		Set<CartItem> items = cart.getItems();
		
		Order order = new Order();
		
		AtomicReference<Double> totalOrderPrice = new AtomicReference<>(0.0);
		
		Set<OrderItem> orderItems = items.stream().map((cartItem)-> {
			
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalProductPrice(cartItem.getTotalProductPrice());
			
			orderItem.setOrder(order);
			totalOrderPrice.set(totalOrderPrice.get()+ orderItem.getTotalProductPrice());
			
			//
            int productId = orderItem.getProduct().getProductId();
            // product:- fetch

            // update the product quantity

            // save the product
			
			
			return orderItem;
			
		}).collect(Collectors.toSet());
		
		order.setItems(orderItems);
		order.setBillingAddress(address);
		order.setPaymentStatus("NOT PAID");
		order.setOrderAmount(totalOrderPrice.get());
		order.setOrderCreated(new Date());
		order.setOrderDelivered(null);
		order.setOrderStatus("CREATED");
		order.setUser(user);
		
		Order savedOder = this.orderRepository.save(order);
		
		cart.getItems().clear();
		this.cartRepository.save(cart);
		
		return this.modelMapper.map(savedOder, OrderDto.class);
	}

	@Override
	public OrderDto updateOrder(OrderDto orderDto, int orderId) {

		Order order = this.orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		String orderStatus = orderDto.getOrderStatus();
		String paymentStatus = orderDto.getPaymentStatus();
		Date orderDelivered = orderDto.getOrderDelivered();
		order.setOrderStatus(orderStatus);
		order.setPaymentStatus(paymentStatus);
		if(order.getOrderStatus().equalsIgnoreCase("Done")) 
			order.setOrderDelivered(new Date());
		else
			order.setOrderDelivered(null);
		
		Order updatedOrder = this.orderRepository.save(order);
		
		return this.modelMapper.map(updatedOrder, OrderDto.class);
	}

	@Override
	public void deleteOrder(int orderId) {

		Order order = this.orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		this.orderRepository.delete(order);
		
	}

	@Override
	public OrderResponse getAll(int pageNumber, int pageSize, String sortby, String sortDir) {
		
        Sort sort = null;
		
		if(sortDir.trim().toLowerCase().equals("asc"))
		{
			sort = Sort.by(sortby).ascending();
		}else
		{
			sort = Sort.by(sortby).descending();
		}
		
		Pageable pageable=  PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Order> page = this.orderRepository.findAll(pageable);
		
		List<Order> all = page.getContent();
	    List<OrderDto> list = all.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
	    
	    OrderResponse response = new OrderResponse();
	    response.setContent(list);
	    response.setPageNumber(page.getNumber());
	    response.setPageSize(page.getSize());
	    response.setTotalElements(page.getTotalElements());
	    response.setTotalPages(page.getTotalPages());
	    response.setLastPage(page.isLast());
	    
	    return response;
		
	}

	@Override
	public OrderDto get(int orderId) {
		Order order = this.orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
		
		return this.modelMapper.map(order, OrderDto.class);
	}

	@Override
    public List<OrderDto> getOrderOfUser(String username) {
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found with given user"));
        List<Order> ordersOfUser = this.orderRepository.findByUser(user);
        return ordersOfUser.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

}
