package com.eduit.bootcamp.springbootapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eduit.bootcamp.springbootapi.api.ProductsApiDelegate;
import com.eduit.bootcamp.springbootapi.model.ProductDTO;
import com.eduit.bootcamp.springbootapi.model.ResponseContainerProductResponseDTO;

public class ProductController implements ProductsApiDelegate {

	public ProductController() {
		// TODO Auto-generated constructor stub
	}

	
	public ResponseEntity<ResponseContainerProductResponseDTO> createProduct(ProductDTO productDTO) {
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<List<ProductDTO>> retrieveAllProducts() {
		return new ResponseEntity<>(Arrays.asList(), HttpStatus.ACCEPTED);
	}
}
