package com.toyshop.StoreToys_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toyshop.StoreToys_API.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
