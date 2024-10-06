package com.toyshop.StoreToys_API.mapper;

import com.toyshop.StoreToys_API.DTOs.request.UserRequest;
import com.toyshop.StoreToys_API.DTOs.respone.UserRespone;
import com.toyshop.StoreToys_API.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserRespone toRespone(User user);

    public List<UserRespone> toListRespones(List<User> users);

    @Mappings({
            @Mapping(target = "userId", ignore = true)
    })
    public void toUser(UserRequest uReq, @MappingTarget User user);
}
