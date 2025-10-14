package com.meow.meowchatting.room.command.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum DirectRoomResponseCode implements MeowCode {

	MEMBER_SIZE_BAD_REQUEST("1:1 채팅은 멤버 1명만 요청할 수 있습니다.", HttpStatus.BAD_REQUEST);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	DirectRoomResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
