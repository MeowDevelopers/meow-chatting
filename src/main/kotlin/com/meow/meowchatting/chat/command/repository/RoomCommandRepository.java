package com.meow.meowchatting.chat.command.repository;

import com.meow.meowchatting.chat.command.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomCommandRepository extends JpaRepository<Room, Long> {
}
