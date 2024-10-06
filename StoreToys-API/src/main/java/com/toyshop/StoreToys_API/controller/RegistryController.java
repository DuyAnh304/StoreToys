package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.RegistryRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registry")
public class RegistryController {

    @Autowired
    private RegistryService registryService;

    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody RegistryRequest registryReq) {
        return ResponseEntity.status(201)
                .body(new APIRespone<>(registryService.registry(registryReq), "Account successfully added"));
    }
}
