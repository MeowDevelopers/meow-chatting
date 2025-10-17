package com.meow.meowchatting.user.command.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum FriendResponseCode implements MeowCode {

	SUCCESS("정상 처리 완료", HttpStatus.OK),

	NOT_FOUND("존재하지 않는 친구입니다.", HttpStatus.NOT_FOUND),

	INVALID_OWNER("해당 친구에 대한 수정 권한이 없습니다.", HttpStatus.FORBIDDEN),;

	private final String responseMessage;

	private final HttpStatus httpStatus;

	FriendResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
