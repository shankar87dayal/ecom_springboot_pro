package com.ecom.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entities.Category;
import com.ecom.entities.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{

	Page<Product> findByCategory(Category category,Pageable pageable);
	
//	 Page<Product> findByCategory(Category category, Pageable pageable);

	    List<Product> findByProductNameContaining(String keywords);

}
