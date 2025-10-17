package com.meow.meowchatting.user.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meow.meowchatting.user.command.domain.Friend;

public interface FriendCommandRepository extends JpaRepository<Friend, Long> {
}
