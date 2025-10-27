package com.meow.meowchatting.user.query.repository;

import com.meow.meowchatting.user.command.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long userId);

}
