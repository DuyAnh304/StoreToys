package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.RegistryRequest;
import com.toyshop.StoreToys_API.DTOs.request.ValidationCodeRequest;
import com.toyshop.StoreToys_API.DTOs.request.ValidationEmailRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.RegistryService;
import com.toyshop.StoreToys_API.service.ValidationEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registry")
@RequiredArgsConstructor
public class RegistryController {

    private final ValidationEmailService vlEmailService;
    private final RegistryService registryService;

    @PostMapping("/validate")
    public ResponseEntity<?> validateRequest(@RequestBody ValidationEmailRequest email) {
        vlEmailService.createValidationCode(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/code")
    public ResponseEntity<?> validateCode(@RequestBody ValidationCodeRequest code) {
        return ResponseEntity.status(200).body(new APIRespone<>(vlEmailService.validateCode(code), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody RegistryRequest registryReq) {
        return ResponseEntity.status(201)
                .body(new APIRespone<>(registryService.registry(registryReq), "Account successfully created"));
    }
}
