package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.CartRequest;
import com.toyshop.StoreToys_API.DTOs.respone.CartRespone;

import java.util.List;

public interface CartService {

    List<CartRespone> getCartByUserId(int userId);

    CartRespone addtoCart(CartRequest request);

    int updateQuantity(int id, CartRequest cReq);

    void deleteCartById(int id);
}
