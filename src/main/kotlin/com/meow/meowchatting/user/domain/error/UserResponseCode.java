package com.meow.meowchatting.user.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum UserResponseCode implements MeowCode {

	NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	UserResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
