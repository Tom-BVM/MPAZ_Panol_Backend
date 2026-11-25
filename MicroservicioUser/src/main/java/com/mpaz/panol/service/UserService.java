package com.mpaz.panol.service;

import com.mpaz.panol.model.User;
import com.mpaz.panol.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User register(User user) {
        if (repo.findByRut(user.getRut()).isPresent()) {
            throw new IllegalArgumentException("RUT ya registrado");
        }
        return repo.save(user);
    }

    public Optional<User> login(String rut, String password) {
        return repo.findByRutAndPassword(rut, password);
    }
}