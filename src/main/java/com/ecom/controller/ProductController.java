package com.ecom.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.AppConstants;
import com.ecom.payload.ApiResonse;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//For creating new Product
		@PostMapping("/categories/{categoryId}/products")
		public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @PathVariable("categoryId") int categoryId) {
			ProductDto createdProduct = productService.createProduct(productDto, categoryId);
			System.out.println("product created");
			return new ResponseEntity<ProductDto>(createdProduct,HttpStatus.CREATED);

		}
		
		//get ALl Prroduct
		/**
		 * <p> this method is written for featch list of category </p>
		 * 
		 * @author Raushan rajnan
		 * @since 1.8
		 * @param Noting
		 * @return listof product
		 */
		
		
		//getting all products
			@GetMapping("/products")
			public ResponseEntity<ProductResponse> getAll(
					@RequestParam(value = "pageNumber", defaultValue = AppConstants.page_number_string, required = false) int pageNumber,
					@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
					@RequestParam(value = "soryby", defaultValue = AppConstants.SORT_BY_STRING,required = false) String sortby,
					@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING,required = false)String sortDir
					
					) {
				
				ProductResponse response = productService.getAll(pageNumber, pageSize, sortby, sortDir);
				return new ResponseEntity<ProductResponse>(response,HttpStatus.OK);
			}
		
		//get single product
		/**
		 * <p> this method is written for featch single product </p>
		 * 
		 * @author Raushan rajnan
		 * @since 1.0
		 * @param productId
		 * @return  product
		 */
		
		@GetMapping("/products/{productId}")
		public ResponseEntity<ProductDto> getProduct(@PathVariable  int productId) {

			ProductDto productDto = this.productService.getproduct(productId);
			return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
		}
		
		//pudate Product
		/**
		 * <p> this method is written for update single product </p>
		 * 
		 * @author Raushan rajnan
		 * @since 1.0
		 * @param productId
		 * @param Product
		 * @return  updateProduct
		 */
		
		

		
		// update
			@PutMapping("/products/{productId}")
			public ResponseEntity<ProductDto> updateProduct(@PathVariable int productId, @RequestBody ProductDto np) {
				ProductDto updatedProduct = productService.updateProduct(np, productId);
				return  new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.OK);

			}
		
		//delete product
		/**
		 * <p> this method is written for delete single product </p>
		 * 
		 * @author Raushan rajnan
		 * @since 1.0
		 * @param productId
		 * @return  "product is successfully Deleted !!"
		 */
		
		@DeleteMapping("/products/{productId}")
		public ResponseEntity<ApiResonse>  deleteProduct(@PathVariable  int productId) {
			this.productService.deleteProduct(productId);
			return new ResponseEntity<ApiResonse>(new ApiResonse("product is deleted successfully !!", true),HttpStatus.OK);
		}
		
		// category wise get product
		@GetMapping("/categories/{categoryId}/products")
		public ResponseEntity<ProductResponse> getProductsOfCategory(@PathVariable int categoryId,
				@RequestParam(value = "pageNumber", defaultValue = AppConstants.page_number_string, required = false) int pageNumber,
				@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
				@RequestParam(value = "soryby", defaultValue = AppConstants.SORT_BY_STRING,required = false) String sortby,
				@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING,required = false)String sortDir
				) {
			ProductResponse listOfProducts = productService.getProductsByCategory(categoryId, pageNumber, pageSize, sortby, sortDir);
			return new ResponseEntity<ProductResponse>(listOfProducts, HttpStatus.CREATED);

		}
}













