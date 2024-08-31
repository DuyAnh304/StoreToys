package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.AccountRequest;
import com.toyshop.StoreToys_API.DTOs.request.RegistryRequest;
import com.toyshop.StoreToys_API.DTOs.respone.AccountRespone;
import com.toyshop.StoreToys_API.mapper.AccountMapper;
import com.toyshop.StoreToys_API.model.Account;
import com.toyshop.StoreToys_API.model.Role;
import com.toyshop.StoreToys_API.model.User;
import com.toyshop.StoreToys_API.repository.AccountRepository;
import com.toyshop.StoreToys_API.repository.RoleRepository;
import com.toyshop.StoreToys_API.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    private AccountRepository aRep;

    @Autowired
    private AccountMapper aMap;

    @Autowired
    private RoleRepository rRep;

    @Override
    public AccountRespone registry(RegistryRequest requestReq) {
        Role existingRole = this.getRoleById(requestReq.getAccountRequest().getRole_id());
        User newUser = User.builder()
                .fullname(requestReq.getUserRequest().getFullname())
                .phone(requestReq.getUserRequest().getPhone())
                .email(requestReq.getUserRequest().getEmail())
                .address(requestReq.getUserRequest().getAddress())
                .sex(requestReq.getUserRequest().getSex())
                .build();
        Account newAccount = Account.builder()
                .username(requestReq.getAccountRequest().getUsername())
                .password(requestReq.getAccountRequest().getPassword())
                .role(existingRole)
                .user(newUser)
                .build();
        return aMap.toResponeWithUser(aRep.save(newAccount));
    }

    private Role getRoleById(int id) {
        return rRep.findById(id).orElseThrow();
    }

}
