package com.meow.meowchatting.user.command.repository;

import com.meow.meowchatting.user.command.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileCommandRepository extends JpaRepository<User, Long> {
}
