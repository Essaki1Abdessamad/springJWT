package com.example.securityjwt.service;

import com.example.securityjwt.model.Role;
import com.example.securityjwt.model.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);
    Role saveRole(Role role);
    User addRoleToUser(String username, String role);
    User getUser(String username);
    List<User> getUsers();
    void deleteUser(User user);
    void deleteRole(Long roleId);
    void deleteRoleFromUser(String username, Long roleId);
    User updateUser(String username, User user);
}
