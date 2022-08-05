package com.ecom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResonse;
import com.ecom.payload.CategoryDto;
import com.ecom.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcat(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	//update
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updatecat(@Valid @RequestBody CategoryDto dtos, @PathVariable int catId)
	{
		CategoryDto update = this.categoryService.update(catId, dtos);
		return new ResponseEntity<CategoryDto>(update, HttpStatus.OK);
	}
	
	
	//getall
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getcat() {
		List<CategoryDto> list = this.categoryService.get();
		return new ResponseEntity<List<CategoryDto>>(list, HttpStatus.OK);
		
	}
	
	//gat single
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getcat(@PathVariable int catId)
	{
		CategoryDto categoryDto = this.categoryService.get(catId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	//delete
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResonse> deletecat(@PathVariable int catId)
	{
		this.categoryService.delete(catId);
		return new ResponseEntity<ApiResonse>(new ApiResonse("Category Deleted Successfully", true),HttpStatus.OK);
	}

	
	
}











