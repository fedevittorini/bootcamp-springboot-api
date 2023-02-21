package com.eduit.bootcamp.springbootapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eduit.bootcamp.springbootapi.api.ProductsApiDelegate;
import com.eduit.bootcamp.springbootapi.model.ProductDTO;
import com.eduit.bootcamp.springbootapi.model.ResponseContainerResponseDTO;

public class ProductController extends BaseController implements ProductsApiDelegate {

	public ProductController() {
		// TODO Auto-generated constructor stub
	}

	
	public ResponseEntity<ResponseContainerResponseDTO> createProduct(ProductDTO productDTO) {
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseContainerResponseDTO> retrieveAllProducts() {
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
