package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.ValidationCodeRequest;
import com.toyshop.StoreToys_API.DTOs.request.ValidationEmailRequest;

public interface ValidationEmailService {


    void createValidationCode(ValidationEmailRequest email);

    String validateCode(ValidationCodeRequest code);
}
