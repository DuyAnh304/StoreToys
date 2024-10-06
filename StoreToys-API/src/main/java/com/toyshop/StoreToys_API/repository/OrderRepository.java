package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
