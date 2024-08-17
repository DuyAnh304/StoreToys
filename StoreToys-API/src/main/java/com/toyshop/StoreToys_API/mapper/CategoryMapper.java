package com.toyshop.StoreToys_API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.toyshop.StoreToys_API.DTOs.respone.CategoryRespone;
import com.toyshop.StoreToys_API.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	@Mappings({
		@Mapping(source = "categoryId", target = "category_id"),
		@Mapping(source = "categoryName", target = "category_name")
    })
	public CategoryRespone toRespone(Category c);
	
	public List<CategoryRespone> toListRespone(List<Category> categories);
	
}
