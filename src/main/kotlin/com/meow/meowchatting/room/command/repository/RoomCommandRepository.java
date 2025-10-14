package com.meow.meowchatting.room.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meow.meowchatting.room.command.domain.Room;

public interface RoomCommandRepository extends JpaRepository<Room, Integer> {
}
