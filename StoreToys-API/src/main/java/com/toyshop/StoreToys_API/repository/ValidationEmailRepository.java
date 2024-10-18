package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.ValidationEmail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationEmailRepository extends MongoRepository<ValidationEmail, String> {

    @Query("{$and: [ { 'code': :#{#code} }, { 'email':  :#{#email} } ]}")
    Optional<ValidationEmail> validateCode(@Param("code") String code, @Param("email") String email);
}
