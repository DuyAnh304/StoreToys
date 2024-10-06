package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<List<Brand>> findByBrandName(String brandName);
}
