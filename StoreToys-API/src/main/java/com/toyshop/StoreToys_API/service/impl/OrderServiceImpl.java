package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.OrderDetailRequest;
import com.toyshop.StoreToys_API.DTOs.request.OrderRequest;
import com.toyshop.StoreToys_API.DTOs.respone.OrderRespone;
import com.toyshop.StoreToys_API.mapper.OrderMapper;
import com.toyshop.StoreToys_API.model.*;
import com.toyshop.StoreToys_API.repository.AccountRepository;
import com.toyshop.StoreToys_API.repository.CartRepository;
import com.toyshop.StoreToys_API.repository.OrderRepository;
import com.toyshop.StoreToys_API.repository.UserRepository;
import com.toyshop.StoreToys_API.service.OrderService;
import com.toyshop.StoreToys_API.util.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orRep;

    private final CartRepository caRep;

    private final UserRepository uRep;

    private final AccountRepository acRep;

    private final OrderMapper mapper;

    @Transactional
    @Override
    public OrderRespone createOrder(OrderRequest orReq) {
        int userId = this.findAccountById(orReq.getUserId()).getUser().getUserId();
        User u = this.getUserById(userId);
        Order order = Order.builder()
                .user(u)
                .customerName(orReq.getCustomerName())
                .customerPhone(orReq.getCustomerPhone())
                .customerAddress(orReq.getCustomerAddress())
                .status(OrderStatus.PLACED)
                .build();
        Set<OrderDetail> list = new HashSet<>();
        for(OrderDetailRequest request : orReq.getListCart()){
            list.add(this.getOrderDetail(request, order));
            caRep.orderProduct(orReq.getUserId(), request.getProduct_id());
        }
        order.setOrderDetails(list);
        return mapper.toOrderRespone(orRep.save(order));
    }

    private OrderDetail getOrderDetail(OrderDetailRequest request, Order order) {
        return OrderDetail.builder()
                .order(order)
                .productId(request.getProduct_id())
                .quantity(request.getQuantity())
                .totalPrice(request.getTotalPrice())
                .build();
    }

    private User getUserById(int id){
        return uRep.findById(id).orElseThrow(); // bat exception
    }

    private Account findAccountById(int id) {
        return acRep.findById(id).orElseThrow();
    }

    private Cart findCartById(int id) {
        return caRep.findById(id).orElseThrow(); // bat exception
    }
}
