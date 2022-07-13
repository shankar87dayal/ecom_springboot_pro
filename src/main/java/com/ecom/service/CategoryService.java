package com.ecom.service;

import java.util.List;

import com.ecom.payload.CategoryDto;



public interface CategoryService {

	 CategoryDto createCategory(CategoryDto categoryDto);
	
	 List<CategoryDto> get();
	
	 CategoryDto get(int categoryId);
	
	 CategoryDto update(int categoryId,CategoryDto cD ); 
	
	 void delete(int categoryId);
	
}
