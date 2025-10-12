package com.meow.meowchatting.user.repository;

import com.meow.meowchatting.user.command.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCommandRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
    Optional<User> findByUserEmail(String userEmail);
}
