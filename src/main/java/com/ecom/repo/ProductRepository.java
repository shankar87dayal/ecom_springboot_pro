package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{

}
