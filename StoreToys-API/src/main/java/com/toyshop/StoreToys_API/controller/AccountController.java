package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.AccountRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService aSer;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(new APIRespone<>(aSer.getAllAccountsWithUsers(), "Request successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body(new APIRespone<>(aSer.getAccount(id), "Request successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody AccountRequest accountReq) {
        return ResponseEntity.status(202)
                .body(new APIRespone<>(aSer.updateAccount(id, accountReq), "Account updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        aSer.deleteAccount(id);
        return ResponseEntity.status(200).body(new APIRespone<>("Account deleted successfully"));
    }

}
