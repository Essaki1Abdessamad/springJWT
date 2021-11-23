package com.example.securityjwt;

import com.example.securityjwt.model.Role;
import com.example.securityjwt.model.User;
import com.example.securityjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Essaki Abdessamad", "saki", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "El achqar Mehdi", "mehdi", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Hindi Zakaria", "zak", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Azzam Oussama", "jack", "1234", new ArrayList<>()));

            userService.addRoleToUser("zak", "ROLE_USER");
            userService.addRoleToUser("zak", "ROLE_MANAGER");
            userService.addRoleToUser("mehdi", "ROLE_MANAGER");
            userService.addRoleToUser("jack", "ROLE_ADMIN");
            userService.addRoleToUser("saki", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("saki", "ROLE_ADMIN");
            userService.addRoleToUser("saki", "ROLE_USER");

        };
    }


}
