package com.toyshop.StoreToys_API.DTOs.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartRespone {

    @JsonProperty("cart_id")
    int id;

    @JsonProperty("product")
    ProductRespone productRespone;

    @JsonProperty("quantity")
    int quantity;
}