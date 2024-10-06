package com.toyshop.StoreToys_API.DTOs.respone;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRespone {

	@JsonProperty("product_id")
	Integer productId;
	
	@JsonProperty("product_name")
	String productName;
	
	@JsonProperty("category_name")
	String categoryName;
	
	@JsonProperty("brand_name")
	String brandName;
	
	@JsonProperty("product_quantity")
	Integer productQuantity;
	
	@JsonProperty("product_image")
	String productImg;
	
	@JsonProperty("product_sex")
	String productSex;
	
	@JsonProperty("product_price")
	Float productPrice;
	
	@JsonProperty("created_at")
	Date createdAt;
	
	@JsonProperty("updated_at")
	Date updatedAt;
}
