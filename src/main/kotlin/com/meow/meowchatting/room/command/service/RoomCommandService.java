package com.meow.meowchatting.room.command.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.room.command.domain.error.DirectRoomResponseCode;
import com.meow.meowchatting.room.command.domain.error.RoomResponseCode;
import com.meow.meowchatting.room.command.dto.CreateRoomRequest;
import com.meow.meowchatting.room.command.dto.CreateRoomResponse;
import com.meow.meowchatting.room.command.repository.RoomCommandRepository;

@Service
@Transactional(readOnly = false)
public class RoomCommandService {

	public RoomCommandService(RoomCommandRepository roomCommandRepository) {
		this.roomCommandRepository = roomCommandRepository;
	}

	private final RoomCommandRepository roomCommandRepository;

	/**
	 * 채팅방 생성
	 */
	// public DataResponse<CreateRoomResponse> createRoom(CreateRoomRequest request) {
	// 	// TODO : UserPrincipal
	//
	// 	switch (request.getRoomType()) {
	// 		case DIRECT -> {
	// 			if (request.getMemberIds().size() > 1) throw new MeowException(DirectRoomResponseCode.MEMBER_SIZE_BAD_REQUEST);
	// 		}
	// 		case GROUP -> {
	// 		}
	// 		default -> throw new MeowException(RoomResponseCode.ROOM_NOT_FOUND);
	// 	}
	//
	//
	// }
}
