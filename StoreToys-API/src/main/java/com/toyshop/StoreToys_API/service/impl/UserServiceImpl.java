package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.UserRequest;
import com.toyshop.StoreToys_API.DTOs.respone.UserRespone;
import com.toyshop.StoreToys_API.mapper.UserMapper;
import com.toyshop.StoreToys_API.model.User;
import com.toyshop.StoreToys_API.repository.UserRepository;
import com.toyshop.StoreToys_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository uRep;

    @Autowired
    private UserMapper uMap;


    @Override
    public List<UserRespone> getAllUsers() {
        return uMap.toListRespones(uRep.findAll());
    }

    @Override
    public UserRespone getUser(int id) {
        return uMap.toRespone(this.getUserById(id));
    }

    @Override
    public UserRespone updateUser(int id, UserRequest uReq) {
        User u = this.getUserById(id);
        uMap.toUser(uReq, u);
        return uMap.toRespone(uRep.saveAndFlush(u));
    }

    private User getUserById(int id) {
        return uRep.findById(id).orElseThrow();
    }
}
