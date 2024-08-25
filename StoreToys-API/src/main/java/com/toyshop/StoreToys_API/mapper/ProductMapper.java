package com.toyshop.StoreToys_API.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.toyshop.StoreToys_API.DTOs.respone.ProductRespone;
import com.toyshop.StoreToys_API.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mappings({
		@Mapping(source = "category.categoryName", target = "categoryName"),
		@Mapping(source = "brand.brandName", target = "brandName")
	})
	public ProductRespone toRespone(Product p); 
	
	public List<ProductRespone> toListRespone(List<Product> products);
}
