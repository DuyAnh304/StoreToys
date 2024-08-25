package com.toyshop.StoreToys_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toyshop.StoreToys_API.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
