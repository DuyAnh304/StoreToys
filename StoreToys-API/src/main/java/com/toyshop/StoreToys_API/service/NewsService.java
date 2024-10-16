package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.NewsRequest;
import com.toyshop.StoreToys_API.DTOs.respone.NewsRespone;

import java.util.List;

public interface NewsService {

    List<NewsRespone> getAllNews();

    NewsRespone getNewsById(String id);

    NewsRespone createNews(NewsRequest newsRequest);
}
