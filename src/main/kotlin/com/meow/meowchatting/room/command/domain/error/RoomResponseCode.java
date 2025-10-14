package com.meow.meowchatting.room.command.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum RoomResponseCode implements MeowCode {

	ROOM_NOT_FOUND("존재하지 않는 채팅방 타입입니다.", HttpStatus.NOT_FOUND);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	RoomResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
