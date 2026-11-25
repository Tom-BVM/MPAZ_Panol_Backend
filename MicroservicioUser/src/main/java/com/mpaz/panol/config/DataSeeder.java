package com.mpaz.panol.config;

import com.mpaz.panol.model.User;
import com.mpaz.panol.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initUsers(UserRepository userRepo) {
        return args -> {
            if (userRepo.count() == 0) {
                userRepo.save(new User("11111111-1", "Carlos Méndez (TI)", "ti.ficticio@mpaz-edu.cl", "1234", "Encargado TI", "ADMIN"));
                userRepo.save(new User("22222222-2", "María González", "mgonzalez.ficticio@mpaz-edu.cl", "1234", "Docente Ciencias", "SOLICITANTE"));
            }
        };
    }
}