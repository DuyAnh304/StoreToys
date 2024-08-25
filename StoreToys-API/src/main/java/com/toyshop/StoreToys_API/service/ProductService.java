package com.toyshop.StoreToys_API.service;

import java.util.List;

import com.toyshop.StoreToys_API.DTOs.request.ProductRequest;
import com.toyshop.StoreToys_API.DTOs.respone.ProductRespone;

public interface ProductService {

	List<ProductRespone> getAllProduct();
	
	ProductRespone getProduct(int id);
	
	ProductRespone addProduct(ProductRequest pR);
	
	ProductRespone updateProduct(int id, ProductRequest pR);
	
	void deleteProduct(int id);
}
