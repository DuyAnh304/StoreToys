package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News, String> {
}
