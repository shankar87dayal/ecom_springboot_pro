package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
