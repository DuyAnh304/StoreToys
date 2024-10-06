package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.UserRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService uSer;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(new APIRespone<>(uSer.getAllUsers(), "Request successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body(new APIRespone<>(uSer.getUser(id), "Request successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody UserRequest uReq) {
        return ResponseEntity.status(202)
                .body(new APIRespone<>(uSer.updateUser(id, uReq), "User updated successfully"));
    }
}
