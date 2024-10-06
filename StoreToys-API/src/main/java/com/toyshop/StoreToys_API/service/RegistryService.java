package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.RegistryRequest;
import com.toyshop.StoreToys_API.DTOs.respone.AccountRespone;

public interface RegistryService {

    AccountRespone registry(RegistryRequest account);
}
