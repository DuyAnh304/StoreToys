package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.OrderRequest;
import com.toyshop.StoreToys_API.DTOs.respone.OrderRespone;

public interface OrderService {

    OrderRespone createOrder(OrderRequest orderRequest);
}
