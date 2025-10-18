package com.meow.meowchatting.user.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meow.meowchatting.user.command.domain.UserProfile;

public interface UserProfileQueryRepository extends JpaRepository<UserProfile, Long> {
}
