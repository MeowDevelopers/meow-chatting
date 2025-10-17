package com.meow.meowchatting.user.command.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum UserProfileResponseCode implements MeowCode {

	NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.NOT_FOUND);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	UserProfileResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
