package com.anirudh.jobtrackerapi.config;

import com.anirudh.jobtrackerapi.model.User;
import com.anirudh.jobtrackerapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("adminpass"));
                admin.setRoles(Set.of("ADMIN"));
                userRepository.save(admin);
            }
        };
    }
}

