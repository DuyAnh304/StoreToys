package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.UserRequest;
import com.toyshop.StoreToys_API.DTOs.respone.UserRespone;

import java.util.List;

public interface UserService {

    List<UserRespone> getAllUsers();

    UserRespone getUser(int id);

    UserRespone getUserByAccountId(int id);

    UserRespone updateUser(int id, UserRequest uReq);

}
