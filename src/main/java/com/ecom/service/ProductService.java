package com.ecom.service;

import java.util.List;

import com.ecom.payload.ProductDto;

public interface ProductService {
	
	public ProductDto createProduct(ProductDto productDto);
	
	public List<ProductDto> getAll();
	
	public ProductDto getproduct(int productId);
	
	public ProductDto updateProduct(ProductDto np, int productId);
	
	public void deleteProduct(int productId);

}
