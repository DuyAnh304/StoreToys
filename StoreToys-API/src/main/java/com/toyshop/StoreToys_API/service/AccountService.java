package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.AccountRequest;
import com.toyshop.StoreToys_API.DTOs.respone.AccountRespone;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    UserDetailsService userDetailsService();

    List<AccountRespone> getAllAccounts();

    List<AccountRespone> getAllAccountsWithUsers();

    AccountRespone getAccount(int id);

    AccountRespone getAccountWithUser(int id);

    AccountRespone updateAccount(int id, AccountRequest account);

    void deleteAccount(int id);
}
