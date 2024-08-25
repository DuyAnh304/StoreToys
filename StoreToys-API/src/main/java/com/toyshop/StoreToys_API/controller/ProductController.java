package com.toyshop.StoreToys_API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyshop.StoreToys_API.DTOs.request.ProductRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService pSer;
	
	@GetMapping(value = {"/", ""})
	public ResponseEntity<?> getAllProduct(){
		return ResponseEntity.ok(new APIRespone<>(pSer.getAllProduct(), "Request successfully"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable int id){
		return ResponseEntity.status(200).body(new APIRespone<>(pSer.getProduct(id), "Request successfully"));
	}
	
	@PostMapping(consumes = {"multipart/form-data"})
	public ResponseEntity<?> addProduct(@ModelAttribute ProductRequest pReq){
		return ResponseEntity.status(201).body(new APIRespone<>(pSer.addProduct(pReq), "Product created successfully"));
	}
	
	@PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
	public ResponseEntity<?> updateProduct(@PathVariable int id, @ModelAttribute ProductRequest pReq) {
		return ResponseEntity.status(202)
				.body(new APIRespone<>(pSer.updateProduct(id, pReq), "Product updated successfully"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		pSer.deleteProduct(id);
		return ResponseEntity.status(200)
				.body(new APIRespone<>("Product deleted successfully"));
	}
}
