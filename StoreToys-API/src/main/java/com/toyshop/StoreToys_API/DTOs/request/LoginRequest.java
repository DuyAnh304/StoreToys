package com.toyshop.StoreToys_API.DTOs.request;

import com.toyshop.StoreToys_API.util.Platform;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    String username;

    String password;

//    Platform platform;

//    String deviceToken;

//    String version;
}
