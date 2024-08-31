package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
