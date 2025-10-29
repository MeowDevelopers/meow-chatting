package com.meow.meowchatting.chat.query.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.chat.command.domain.RoomUsers;
import com.meow.meowchatting.chat.command.domain.error.RoomUsersResponseCode;
import com.meow.meowchatting.chat.query.repository.RoomUsersQueryRepository;
import com.meow.meowchatting.common.exception.MeowException;

@Service
@Transactional(readOnly = true)
public class RoomUsersQueryService {

	private final RoomUsersQueryRepository roomUsersQueryRepository;

	public RoomUsersQueryService(RoomUsersQueryRepository roomUsersQueryRepository) {
		this.roomUsersQueryRepository = roomUsersQueryRepository;
	}

	/**
	 * 채팅방 접근 권한 검증
	 */
	public void validateRoomUsers(Long userId, Long roomId) {
		RoomUsers roomUsers = roomUsersQueryRepository.findByUserIdAndRoomId(userId, roomId)
			.orElseThrow(() -> new MeowException(RoomUsersResponseCode.USER_NOT_IN_CHAT_ROOM));

		if (!roomUsers.getIsActive()) {
			throw new MeowException(RoomUsersResponseCode.DISABLED_CHAT_ROOM);
		}
	}

}
