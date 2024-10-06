package com.toyshop.StoreToys_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toyshop.StoreToys_API.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.category.categoryName = :name")
    List<Product> findByCategoryName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.brand.brandName = :name")
    Optional<List<Product>> findByBrandName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.productSex = :sex")
    Optional<List<Product>> findBySex(@Param("sex") String sex);
}
