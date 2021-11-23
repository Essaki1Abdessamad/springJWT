package com.example.securityjwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.securityjwt.model.Role;
import com.example.securityjwt.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.securityjwt.repository.RoleRepo;
import com.example.securityjwt.repository.UserRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.info("User found in the database: {}",username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving user "+user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role "+role.getName());
        return roleRepo.save(role);
    }

    @Override
    public User addRoleToUser(String username, String roleName) {
        log.info("Adding role "+roleName+" to user "+username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        return user;

    }



    @Override
    public User getUser(String username) {
        log.info("Fetching user by name "+username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);

    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepo.getById(roleId);
        userRepo.findAll().stream()
                .forEach(user->user.getRoles().remove(role));
        roleRepo.delete(role);

    }

    @Override
    public void deleteRoleFromUser(String username, Long roleId) {
        Role role = roleRepo.getById(roleId);
        User user = userRepo.findByUsername(username);
        user.getRoles().remove(role);
    }

    @Override
    public User updateUser(String username, User newUser) {
        User user = userRepo.findByUsername(username);
        if(newUser.getUsername()!=null) user.setUsername(newUser.getUsername());
        if(newUser.getPassword()!=null) user.setPassword(newUser.getPassword());
        if(newUser.getUser()!=null) user.setUser(newUser.getUser());
        return user;
    }


}
