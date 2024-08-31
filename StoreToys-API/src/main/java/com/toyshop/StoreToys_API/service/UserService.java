package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.UserRequest;
import com.toyshop.StoreToys_API.DTOs.respone.UserRespone;

import java.util.List;

public interface UserService {

    public List<UserRespone> getAllUsers();

    public UserRespone getUser(int id);

    public UserRespone updateUser(int id, UserRequest uReq);

}
