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
public class APIRespone<T> {

	int status;
	String message;
	Date timestamp;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	T data;

	// Constructor với ngày giờ hiện tại
	// Dành cho POST, PUT, GET
	public APIRespone(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.timestamp = new Date(); // Lấy ngày giờ hiện tại
	}

	// Constructor với ngày giờ hiện tại
	// Dành cho DELETE
	public APIRespone(int status, String message) {
		this.status = status;
		this.message = message;
		this.timestamp = new Date(); // Lấy ngày giờ hiện tại
	}
}
