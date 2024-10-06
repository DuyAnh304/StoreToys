package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.LoginRequest;
import com.toyshop.StoreToys_API.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService auSer;

    @PostMapping("/access")
    public ResponseEntity<?> access(@RequestBody LoginRequest lReq) {
        return ResponseEntity.ok(auSer.authenticate(lReq));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(auSer.refresh(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        auSer.logout(request);
        return ResponseEntity.status(204).build();
    }
}
