package com.toyshop.StoreToys_API.DTOs.request;

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
public class UserRequest {

    @JsonProperty("fullname")
    String fullname;

    @JsonProperty("phone")
    String phone;

    @JsonProperty("address")
    String address;

    @JsonProperty("sex")
    String sex;

    @JsonProperty("email")
    String email;
}
