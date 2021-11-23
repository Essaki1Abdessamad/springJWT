package com.example.securityjwt.repository;

import com.example.securityjwt.model.Role;
import com.example.securityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findByRolesIn(List<Role> roles);
}
