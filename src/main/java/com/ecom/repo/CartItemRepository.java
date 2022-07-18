package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Cart;
import com.ecom.entities.CartItem;
import com.ecom.entities.User;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	List<Cart> findByCart(Cart cart);
//	List<User> findByUser(User userId);
	
}
