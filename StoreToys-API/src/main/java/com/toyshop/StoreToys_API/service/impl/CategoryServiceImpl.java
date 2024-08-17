package com.toyshop.StoreToys_API.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyshop.StoreToys_API.DTOs.request.CategoryRequest;
import com.toyshop.StoreToys_API.DTOs.respone.CategoryRespone;
import com.toyshop.StoreToys_API.mapper.CategoryMapper;
import com.toyshop.StoreToys_API.model.Category;
import com.toyshop.StoreToys_API.repository.CategoryRepository;
import com.toyshop.StoreToys_API.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cR;
	
	@Autowired
	private CategoryMapper cM;
	
	
	@Override
	public List<CategoryRespone> getAllCategory() {
		// TODO Auto-generated method stubs
		return cM.toListRespone(cR.findAll());
	}

	@Override
	public CategoryRespone getCategory(int id) {
		// TODO Auto-generated method stub
		Category c = this.getById(id);
		return cM.toRespone(c);
	}

	@Override
	public CategoryRespone addCategory(CategoryRequest cReq) {
		// TODO Auto-generated method stub
		Category c = Category.builder()
				.categoryName(cReq.getCategoryName())
				.build();
		return cM.toRespone(cR.save(c));
	}

	@Override
	public CategoryRespone updateCategory(int id, CategoryRequest cReq) {
		// TODO Auto-generated method stub
		Category c = this.getById(id);
		c.setCategoryName(cReq.getCategoryName());
		return cM.toRespone(cR.saveAndFlush(c));
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		this.getById(id);
		cR.deleteById(id);
	}
	
	private Category getById(int id) {
		Category c = cR.findById(id).orElseThrow(() -> new RuntimeException("Category not found: " + id));
		return c;
	}

}
