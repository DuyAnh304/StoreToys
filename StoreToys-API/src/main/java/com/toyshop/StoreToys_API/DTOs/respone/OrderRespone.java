package com.toyshop.StoreToys_API.DTOs.respone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toyshop.StoreToys_API.util.OrderStatus;
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
public class OrderRespone {

    @JsonProperty("order_id")
    Integer id;

    @JsonProperty("user_id")
    Integer userId;

    @JsonProperty("customer_name")
    String customerName;

    @JsonProperty("customer_phone")
    String customerPhone;

    @JsonProperty("customer_address")
    String customerAddress;

    @JsonProperty("status")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    OrderStatus status;
}
