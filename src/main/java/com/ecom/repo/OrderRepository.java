package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
