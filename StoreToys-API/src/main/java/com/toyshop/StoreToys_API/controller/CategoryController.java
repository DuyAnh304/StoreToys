package com.toyshop.StoreToys_API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public APIRespone<?> getAllCategory() {
		return new APIRespone<>(HttpStatus.OK.value(), "Request successfully", cS.getAllCategory());
	}
	
	@GetMapping("/{id}")
	public APIRespone<?> getByID(@PathVariable int id) {
		return new APIRespone<>(HttpStatus.OK.value(), "Request successfully", cS.getCategory(id));
	}
	
	@PostMapping
	public APIRespone<?> addCategory(@RequestBody CategoryRequest cR) {
		return new APIRespone<>(HttpStatus.CREATED.value(), "Category created successfully",
				cS.addCategory(cR));
	}
	
	@PutMapping("/{id}")
	public APIRespone<?> updateCategory(@RequestBody CategoryRequest cR, @PathVariable int id) {
		return new APIRespone<>(HttpStatus.ACCEPTED.value(), "Category updated successfully",
				cS.updateCategory(id, cR));
	}
	
	@DeleteMapping("/{id}")
	public APIRespone<?> deleteCategory(@PathVariable int id) {
		cS.deleteCategory(id);
		return new APIRespone<>(HttpStatus.NO_CONTENT.value(), "Category deleted successfully");
	}
}
