package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Category;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CategoryDto;
import com.ecom.repo.CategoryRepository;
import com.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> get() {
		List<Category> all = this.categoryRepository.findAll();
		List<CategoryDto> dtos = all.stream().map((cat) ->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public CategoryDto get(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category with " + categoryId + " not found on server !!"));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto update(int categoryId, CategoryDto cD) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category with " + categoryId + " not found on server !!"));
		
		category.setTitle(cD.getTitle());
		category.setCategoryDesc(cD.getCategoryDesc());
		Category updatedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void delete(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category with " + categoryId + " not found on server !!"));
		
		 this.categoryRepository.delete(category);
	}

}
