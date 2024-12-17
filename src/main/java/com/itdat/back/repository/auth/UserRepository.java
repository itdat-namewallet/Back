package com.itdat.back.repository.auth;

import com.itdat.back.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
    Optional<User> findByUserEmail(String email);

    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String email);
}