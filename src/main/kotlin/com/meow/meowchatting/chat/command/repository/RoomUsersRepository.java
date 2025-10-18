package com.meow.meowchatting.chat.command.repository;

import com.meow.meowchatting.chat.command.domain.RoomUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUsersRepository extends JpaRepository<RoomUsers, Long> {
}
