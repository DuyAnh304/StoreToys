package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.AccountRequest;
import com.toyshop.StoreToys_API.DTOs.respone.AccountRespone;
import com.toyshop.StoreToys_API.mapper.AccountMapper;
import com.toyshop.StoreToys_API.model.Account;
import com.toyshop.StoreToys_API.model.Role;
import com.toyshop.StoreToys_API.repository.AccountRepository;
import com.toyshop.StoreToys_API.repository.CartRepository;
import com.toyshop.StoreToys_API.repository.RoleRepository;
import com.toyshop.StoreToys_API.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository aRep;

    private final RoleRepository rRep;

    private final AccountMapper aMap;

    private final CartRepository cRep;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> aRep.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public List<AccountRespone> getAllAccounts() {
        return aMap.toListRespones(aRep.findAll());
    }

    public List<AccountRespone> getAllAccountsWithUsers() {
        return aMap.toListResponesWithUsers(aRep.findAll());
    }

    public AccountRespone getAccount(int id) {
        return aMap.toRespone(this.getAccountById(id));
    }

    public AccountRespone getAccountWithUser(int id) {
        return aMap.toResponeWithUser(this.getAccountById(id));
    }

    @Override
    public AccountRespone updateAccount(int id, AccountRequest aReq) {
        Role existingRole = this.getRoleById(aReq.getRole_id());
        Account existingAccount = this.getAccountById(id);
        existingAccount.setUsername(aReq.getUsername());
        existingAccount.setPassword(aReq.getPassword());
        existingAccount.setRole(existingRole);
        return aMap.toRespone(aRep.saveAndFlush(existingAccount));
    }

    @Override
    public void deleteAccount(int id) {
        int userId = this.getAccountById(id).getUser().getUserId();
        if(cRep.findByUserId(userId).isPresent()) {
            cRep.deleteByUserId(userId);
        }
        aRep.deleteById(id);
    }

    private Account getAccountById(int id) {
        return aRep.findById(id).orElseThrow();
    }

    private Role getRoleById(int id) {
        return rRep.findById(id).orElseThrow();
    }
}