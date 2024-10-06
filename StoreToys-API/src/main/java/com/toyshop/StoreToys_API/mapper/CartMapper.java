package com.toyshop.StoreToys_API.mapper;

import com.toyshop.StoreToys_API.DTOs.respone.CartRespone;
import com.toyshop.StoreToys_API.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {ProductMapper.class})
public interface CartMapper {

    @Mappings({
            @Mapping(source = "product", target = "productRespone", qualifiedByName = "useForCartService")
    })
    CartRespone toResponse(Cart cart);

    List<CartRespone> toListResponse(List<Cart> cartList);
}
