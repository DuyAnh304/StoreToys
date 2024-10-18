package com.toyshop.StoreToys_API.mapper;

import com.toyshop.StoreToys_API.DTOs.request.NewsRequest;
import com.toyshop.StoreToys_API.DTOs.respone.NewsRespone;
import com.toyshop.StoreToys_API.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NewsMapper {

    NewsRespone toNewsRespone(News news);

    List<NewsRespone> toNewsResponeList(List<News> newsList);
}
