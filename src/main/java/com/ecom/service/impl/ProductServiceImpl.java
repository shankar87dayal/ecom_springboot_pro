package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Product;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ProductDto;
import com.ecom.repo.ProductRepository;
import com.ecom.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProductDto createProduct(ProductDto productDto) {
		
		Product product = this.modelMapper.map(productDto, Product.class);
		Product createdProduct = this.productRepository.save(product);
		return this.modelMapper.map(createdProduct, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAll() {
		List<Product> all = this.productRepository.findAll();
		List<ProductDto> list = all.stream().map(product ->this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public ProductDto getproduct(int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with " + productId + " not found on server !!"));
		
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto np, int productId) {
		Product p = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with " + productId + " not found on server !!"));
		
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

}
