package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.CartRequest;
import com.toyshop.StoreToys_API.DTOs.respone.CartRespone;
import com.toyshop.StoreToys_API.mapper.CartMapper;
import com.toyshop.StoreToys_API.model.Cart;
import com.toyshop.StoreToys_API.model.Product;
import com.toyshop.StoreToys_API.repository.CartRepository;
import com.toyshop.StoreToys_API.repository.ProductRepository;
import com.toyshop.StoreToys_API.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cRep;
    private final CartMapper cMap;
    private final ProductRepository prRep;

    @Override
    public List<CartRespone> getCartByUserId(int userId) {
        return cMap.toListResponse(cRep.findByUserId(userId).orElseThrow()); // can bat exception
    }

    @Override
    public CartRespone addtoCart(CartRequest request) {
        Cart c = Cart.builder()
                .userId(request.getUser_id())
                .product(this.findProductById(request.getProduct_id()))
                .quantity(request.getQuantity())
                .build();
        return cMap.toResponse(cRep.save(c));
    }

    @Transactional
    @Override
    public int updateQuantity(int id, CartRequest cReq) {
        return cRep.updateQuantity(cReq.getQuantity(), id, cReq.getUser_id(), cReq.getProduct_id());
    }

    @Override
    public void deleteCartById(int id) {
        this.findCartById(id);
        cRep.deleteById(id);
    }

    private Product findProductById(int id) {
        return prRep.findById(id).orElseThrow(); //can bat ngoai le
    }

    private Cart findCartById(int id) {
        return cRep.findById(id).orElseThrow(); // bat ngoai le
    }
}
