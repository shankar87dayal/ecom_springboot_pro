package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.entities.Category;
import com.ecom.entities.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.repo.CategoryRepository;
import com.ecom.repo.ProductRepository;
import com.ecom.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public ProductDto createProduct(ProductDto productDto, int categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
		.orElseThrow(() -> new ResourceNotFoundException("not found this category !!"));
		
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setCategory(category);
		Product createdProduct = this.productRepository.save(product);
		return this.modelMapper.map(createdProduct, ProductDto.class);
	}

	@Override
	public ProductResponse getAll(int pageNumber, int pageSize, String sortby, String sortDir) {
		
		Sort sort = null;
		
		if(sortDir.trim().toLowerCase().equals("asc"))
		{
			sort = Sort.by(sortby).ascending();
		}else
		{
			sort = Sort.by(sortby).descending();
		}
		
		Pageable pageable= PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Product> page = this.productRepository.findAll(pageable);
		List<Product> all = page.getContent();
		List<ProductDto> list = all.stream().map(product ->this.modelMapper.map(product, ProductDto.class))
		.collect(Collectors.toList());
		
		ProductResponse response = new ProductResponse();
		response.setContent(list);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		
		return response;
	}

	@Override
	public ProductDto getproduct(int productId) {
		Product product = this.productRepository
			.findById(productId)
			.orElseThrow(()-> new ResourceNotFoundException("Product with " + productId + " not found on server !!"));
		
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto np, int productId) {
		Product p = this.productRepository.findById(productId)
			.orElseThrow(()-> new ResourceNotFoundException("Product with " + productId + " not found on server !!"));
		
		p.setProductName(np.getProductName());
		p.setProductDesc(np.getProductDesc());
		p.setProductPrice(np.getProductPrice());
		p.setLive(np.isLive());
		p.setStock(np.isStock());
		p.setImageName(np.getImageName());
		
		Product updatedProduct = this.productRepository.save(p);
		
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(int productId) {

		Product p = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with " + productId + " not found on server !!"));
		
		
		this.productRepository.delete(p);
	}

	@Override
	public ProductResponse getProductsByCategory(int categoryId, int pageNumber, int pageSize, String sortby, String sortDir) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("not found this category !!"));
		
       Sort sort = null;
		
		if(sortDir.trim().toLowerCase().equals("asc"))
		{
			sort = Sort.by(sortby).ascending();
		}else
		{
			sort = Sort.by(sortby).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Product> page = this.productRepository.findByCategory(category, pageable);
		List<Product> categories = page.getContent();
		List<ProductDto> dtos = categories.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		ProductResponse response = new ProductResponse();
		response.setContent(dtos);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		
		return response;
		
		
	}

}
