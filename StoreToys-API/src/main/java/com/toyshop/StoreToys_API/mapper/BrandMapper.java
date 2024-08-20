package com.toyshop.StoreToys_API.mapper;

import com.toyshop.StoreToys_API.DTOs.respone.BrandRespone;
import com.toyshop.StoreToys_API.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mappings({
            @Mapping(source = "brandId", target = "brand_id"),
            @Mapping(source = "brandName", target = "brand_name"),
            @Mapping(source = "brandImg", target = "brand_img")
    })
    public BrandRespone toRespone(Brand b);

    public List<BrandRespone> toListRespone(List<Brand> brands);
}
