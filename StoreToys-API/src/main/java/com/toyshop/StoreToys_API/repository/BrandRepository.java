package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {


}
