package com.toyshop.StoreToys_API.DTOs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    @JsonProperty("user_id")
    Integer userId;

    @JsonProperty("customer_name")
    String customerName;

    @JsonProperty("customer_phone")
    String customerPhone;

    @JsonProperty("customer_address")
    String customerAddress;

    @JsonProperty("note")
    String note;

    @JsonProperty("cart")
    Set<OrderDetailRequest> listCart;
}
