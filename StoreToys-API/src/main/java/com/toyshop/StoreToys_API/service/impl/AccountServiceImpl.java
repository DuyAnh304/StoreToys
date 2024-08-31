package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.AccountRequest;
import com.toyshop.StoreToys_API.DTOs.respone.AccountRespone;
import com.toyshop.StoreToys_API.mapper.AccountMapper;
import com.toyshop.StoreToys_API.model.Account;
import com.toyshop.StoreToys_API.model.Role;
import com.toyshop.StoreToys_API.repository.AccountRepository;
import com.toyshop.StoreToys_API.repository.RoleRepository;
import com.toyshop.StoreToys_API.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository aRep;

    @Autowired
    private RoleRepository rRep;

    @Autowired
    private AccountMapper aMap;

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
        this.getAccountById(id);
        aRep.deleteById(id);
    }

    private Account getAccountById(int id) {
        return aRep.findById(id).orElseThrow();
    }

    private Role getRoleById(int id) {
        return rRep.findById(id).orElseThrow();
    }
}