package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.OrderDetailRequest;
import com.toyshop.StoreToys_API.DTOs.request.OrderRequest;
import com.toyshop.StoreToys_API.DTOs.respone.OrderRespone;
import com.toyshop.StoreToys_API.mapper.OrderMapper;
import com.toyshop.StoreToys_API.model.Cart;
import com.toyshop.StoreToys_API.model.Order;
import com.toyshop.StoreToys_API.model.OrderDetail;
import com.toyshop.StoreToys_API.model.User;
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

    private final OrderMapper mapper;

    @Transactional
    @Override
    public OrderRespone createOrder(OrderRequest orReq) {
        User u = this.getUserById(orReq.getUserId());
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

    private Cart findCartById(int id) {
        return caRep.findById(id).orElseThrow(); // bat exception
    }
}
