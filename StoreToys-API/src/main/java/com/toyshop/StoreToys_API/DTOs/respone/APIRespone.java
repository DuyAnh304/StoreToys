package com.toyshop.StoreToys_API.DTOs.respone;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class APIRespone<T> {

	T data;
	String message;
	Date timestamp;

	// Constructor với ngày giờ hiện tại
	// Dành cho POST, PUT, GET
	public APIRespone(T data, String message) {
		this.message = message;
		this.data = data;
		this.timestamp = new Date(); // Lấy ngày giờ hiện tại
	}

	// Constructor với ngày giờ hiện tại
	// Dành cho DELETE
	public APIRespone(String message) {
		this.message = message;
		this.timestamp = new Date(); // Lấy ngày giờ hiện tại
	}
}
