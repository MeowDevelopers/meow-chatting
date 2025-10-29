package com.meow.meowchatting.chat.command.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum RoomUsersResponseCode implements MeowCode {

	SUCCESS("정상 처리 완료", HttpStatus.OK),

	USER_NOT_IN_CHAT_ROOM("채팅방에 참여하지 않은 사용자입니다.", HttpStatus.BAD_REQUEST),

	DISABLED_CHAT_ROOM("비활성화된 채팅방입니다.", HttpStatus.FORBIDDEN);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	RoomUsersResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
