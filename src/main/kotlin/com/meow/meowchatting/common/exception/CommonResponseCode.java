package com.meow.meowchatting.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum CommonResponseCode implements MeowCode {

	INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

	TYPE_MISMATCH("요청 파라미터 형식이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),

	VALIDATION_FAILED("유효성 검증 실패", HttpStatus.BAD_REQUEST);

	private final String responseMessage;

	private final HttpStatus httpStatus;

	CommonResponseCode(String responseMessage, HttpStatus httpStatus) {
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
