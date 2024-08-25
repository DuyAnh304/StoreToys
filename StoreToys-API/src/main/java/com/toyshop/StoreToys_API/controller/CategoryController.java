package com.toyshop.StoreToys_API.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.toyshop.StoreToys_API.DTOs.request.CategoryRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService cS;
	
	@GetMapping("/")
	public ResponseEntity<?> getAllCategory(){
		return ResponseEntity.ok(new APIRespone<>(cS.getAllCategory(), "Request successfully"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getByID(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(new APIRespone<>(cS.getCategory(id), "Request successfully"));
	}
	
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody CategoryRequest cR) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new APIRespone<>(cS.addCategory(cR), "Category created successfully"));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryRequest cR, @PathVariable int id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new APIRespone<>(cS.updateCategory(id, cR), "Category updated successfully"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
		cS.deleteCategory(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new APIRespone<>("Category deleted successfully"));
	}
}
