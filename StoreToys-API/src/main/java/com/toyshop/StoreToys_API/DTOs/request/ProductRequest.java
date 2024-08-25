package com.toyshop.StoreToys_API.DTOs.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

	String product_name;
	
	Integer category_id;
	
	Integer brand_id;
	
	Integer product_quantity;
	
	MultipartFile product_img;
	
	String product_sex;
	
	Float product_price;
}
