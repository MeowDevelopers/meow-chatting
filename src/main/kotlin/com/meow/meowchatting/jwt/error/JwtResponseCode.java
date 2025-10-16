package com.meow.meowchatting.jwt.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum JwtResponseCode implements MeowCode {

	EXPIRED("만료된 토큰입니다.", HttpStatus.BAD_REQUEST);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	JwtResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
