package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Order;
import com.ecom.entities.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUser(User user);
}
