package com.mpaz.panol.repository;

import com.mpaz.panol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRutAndPassword(String rut, String password);
    Optional<User> findByRut(String rut);
}