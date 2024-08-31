package com.toyshop.StoreToys_API.DTOs.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRespone {

    @JsonProperty("user_id")
    Integer userId;

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
