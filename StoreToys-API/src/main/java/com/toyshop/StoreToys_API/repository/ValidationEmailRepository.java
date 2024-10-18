package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.ValidationEmail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ValidationEmailRepository extends CrudRepository<ValidationEmail, String> {

    Optional<ValidationEmail> findByValidationCodeAndEmail(String code, String email);

    Optional<ValidationEmail> findByIdAndEmail(String id, String email);
}
