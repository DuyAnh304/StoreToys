package com.toyshop.StoreToys_API.DTOs.respone;

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
public class AccountRespone {

    @JsonProperty("account_id")
    Integer accountId;

    @JsonProperty("username")
    String username;

    @JsonProperty("password")
    String password;

    @JsonProperty("role_name")
    String roleName;

    @JsonProperty("user")
    UserRespone userRespone;
}
