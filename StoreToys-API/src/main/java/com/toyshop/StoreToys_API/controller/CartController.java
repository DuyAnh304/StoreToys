package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.CartRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.DTOs.respone.CartRespone;
import com.toyshop.StoreToys_API.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cSer;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartByUserId(@PathVariable int id) {
        return ResponseEntity.status(200)
                .body(new APIRespone<>(cSer.getCartByUserId(id), "Request successfully"));
    }

    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody CartRequest request) {
        return ResponseEntity.status(201)
                .body(new APIRespone<>(cSer.addtoCart(request), "Add to cart successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable int id, @RequestBody CartRequest cReq) {
        return ResponseEntity.status(202)
                .body(new APIRespone<>(cSer.updateQuantity(id, cReq), "Cart updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id) {
        cSer.deleteCartById(id);
        return ResponseEntity.status(200).body(new APIRespone<>("Cart deleted successfully"));
    }
}
