package com.meow.meowchatting.chat.query.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meow.meowchatting.chat.command.domain.RoomUsers;

public interface RoomUsersQueryRepository extends JpaRepository<RoomUsers, Long> {

	Optional<RoomUsers> findByUserIdAndRoomId(Long userId, Long roomId);

}
