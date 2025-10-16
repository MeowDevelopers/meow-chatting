package com.meow.meowchatting.user.query.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meow.meowchatting.user.command.domain.User;

public interface UserQueryRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long userId);

}
