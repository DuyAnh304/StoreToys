package com.toyshop.StoreToys_API.DTOs.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
	
	@JsonProperty("category_name")
	private String categoryName;
}
