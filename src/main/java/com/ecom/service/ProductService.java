package com.ecom.service;

import java.util.List;

import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;

public interface ProductService {
	
	public ProductDto createProduct(ProductDto productDto, int categoryId);
	
	public ProductResponse getAll(int pageNumber, int pageSize , String sortby, String sortDir);
	
	public ProductDto getproduct(int productId);
	
	public ProductDto updateProduct(ProductDto np, int productId);
	
	public void deleteProduct(int productId);

	public ProductResponse getProductsByCategory(int categoryId,int pageNumber,int pageSize,String sortby, String sortDir);
	
	public void searchProduct(int productId);

}
