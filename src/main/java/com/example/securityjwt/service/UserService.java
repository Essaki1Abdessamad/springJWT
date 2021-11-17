package com.example.securityjwt.service;

import com.example.securityjwt.model.Role;
import com.example.securityjwt.model.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User getUser(String userName);
    List<User> getUsers();
}
