package com.toyshop.StoreToys_API.mapper;

import java.util.List;

import org.mapstruct.*;

import com.toyshop.StoreToys_API.DTOs.respone.ProductRespone;
import com.toyshop.StoreToys_API.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Named("useForProductService")
	@Mappings({
		@Mapping(source = "category.categoryName", target = "categoryName"),
		@Mapping(source = "brand.brandName", target = "brandName")
	})
	ProductRespone toRespone(Product p);

	@IterableMapping(qualifiedByName = "useForProductService")
	List<ProductRespone> toListRespone(List<Product> products);

	@Named("useForCartService")
	@Mappings({
			@Mapping(target = "categoryName", ignore = true),
			@Mapping(target = "brandName", ignore = true),
			@Mapping(target = "productQuantity", ignore = true),
			@Mapping(target = "productSex", ignore = true),
			@Mapping(target = "createdAt", ignore = true),
			@Mapping(target = "updatedAt", ignore = true),
	})
	ProductRespone toResponeInCart(Product p);
}
