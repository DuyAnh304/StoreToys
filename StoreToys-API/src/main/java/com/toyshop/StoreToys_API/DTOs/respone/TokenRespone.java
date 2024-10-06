package com.toyshop.StoreToys_API.DTOs.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenRespone {

    String accessToken; //Key cho phep truy cap he thong

    String refreshToken; //Lam moi token

    String role;

    Integer userId;
}
