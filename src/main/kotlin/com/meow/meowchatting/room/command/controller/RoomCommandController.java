package com.meow.meowchatting.room.command.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.room.command.dto.CreateRoomRequest;
import com.meow.meowchatting.room.command.dto.CreateRoomResponse;
import com.meow.meowchatting.room.command.service.RoomCommandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/user/chat/rooms", name = "채팅방 command 컨트롤러")
public class RoomCommandController {

	private final RoomCommandService roomCommandService;

	public RoomCommandController(RoomCommandService roomCommandService) {
		this.roomCommandService = roomCommandService;
	}

	// @PostMapping(name = "채팅방 생성")
	// public DataResponse<CreateRoomResponse> createRoom(@Valid @RequestBody CreateRoomRequest request) {
	// 	return roomCommandService.createRoom(request);
	// }

}
