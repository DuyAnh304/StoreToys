package com.toyshop.StoreToys_API.service;

import java.util.List;

import com.toyshop.StoreToys_API.DTOs.request.CategoryRequest;
import com.toyshop.StoreToys_API.DTOs.respone.CategoryRespone;

public interface CategoryService {

	List<CategoryRespone> getAllCategory();
	
	CategoryRespone getCategory(int id);
	
	CategoryRespone addCategory(CategoryRequest cReq);
	
	CategoryRespone updateCategory(int id, CategoryRequest c);
	
	void deleteCategory(int id);
	
}
