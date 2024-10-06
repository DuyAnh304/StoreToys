package com.toyshop.StoreToys_API.mapper;

import com.toyshop.StoreToys_API.DTOs.respone.OrderRespone;
import com.toyshop.StoreToys_API.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "user.userId", target = "userId")
    })
    OrderRespone toOrderRespone(Order order);
}
