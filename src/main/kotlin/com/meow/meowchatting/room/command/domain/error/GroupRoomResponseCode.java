package com.meow.meowchatting.room.command.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum GroupRoomResponseCode implements MeowCode {

	ROOM_NAME_IS_REQUIRED("채팅방 이름은 필수입니다.", HttpStatus.BAD_REQUEST);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	GroupRoomResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
