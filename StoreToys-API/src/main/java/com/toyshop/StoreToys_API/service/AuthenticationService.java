package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.LoginRequest;
import com.toyshop.StoreToys_API.DTOs.respone.TokenRespone;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    TokenRespone authenticate(LoginRequest loginRequest);

    TokenRespone refresh(HttpServletRequest request);

    void logout(HttpServletRequest request);
}
