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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistryServiceImpl implements RegistryService {

    private final AccountRepository aRep;

    private final AccountMapper aMap;

    private final RoleRepository rRep;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountRespone registry(RegistryRequest requestReq) {
        Role existingRole = this.getRoleById(requestReq.getAccountRequest().getRole_id());
        String encodedPass = passwordEncoder.encode(requestReq.getAccountRequest().getPassword());
        User newUser = User.builder()
                .fullname(requestReq.getUserRequest().getFullname())
                .phone(requestReq.getUserRequest().getPhone())
                .email(requestReq.getUserRequest().getEmail())
                .address(requestReq.getUserRequest().getAddress())
                .sex(requestReq.getUserRequest().getSex())
                .build();
        Account newAccount = Account.builder()
                .username(requestReq.getAccountRequest().getUsername())
                .password(encodedPass)
                .role(existingRole)
                .user(newUser)
                .build();
        return aMap.toResponeWithUser(aRep.save(newAccount));
    }

    private Role getRoleById(int id) {
        return rRep.findById(id).orElseThrow();
    }

}
