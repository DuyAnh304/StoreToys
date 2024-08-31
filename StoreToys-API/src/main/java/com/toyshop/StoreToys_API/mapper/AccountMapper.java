package com.toyshop.StoreToys_API.mapper;

import com.toyshop.StoreToys_API.DTOs.respone.AccountRespone;
import com.toyshop.StoreToys_API.model.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {UserMapper.class})
public interface AccountMapper {

    @Named("responeWithUser")
    @Mappings({
            @Mapping(source = "role.roleName", target = "roleName"),
            @Mapping(source = "user", target = "userRespone")
    })
    public AccountRespone toResponeWithUser(Account account);

    @IterableMapping(qualifiedByName = "responeWithUser")
    public List<AccountRespone> toListResponesWithUsers(List<Account> accounts);

    @Named("responeWithoutUser")
    @Mappings({
            @Mapping(source = "role.roleName", target = "roleName"),
            @Mapping(source = "user", target = "userRespone", ignore = true)
    })
    public AccountRespone toRespone(Account account);

    @IterableMapping(qualifiedByName = "responeWithoutUser")
    public List<AccountRespone> toListRespones(List<Account> accounts);
}
