package com.ecom.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Cart;
import com.ecom.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Optional<Cart> findByUser(User user);
}
