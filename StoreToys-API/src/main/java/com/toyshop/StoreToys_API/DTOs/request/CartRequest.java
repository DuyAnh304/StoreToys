package com.toyshop.StoreToys_API.DTOs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {

    @JsonProperty("user_id")
    Integer user_id;

    @JsonProperty("product_id")
    Integer product_id;

    @JsonProperty("quantity")
    Integer quantity;
}
