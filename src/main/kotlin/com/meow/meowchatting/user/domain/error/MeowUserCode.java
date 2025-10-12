package com.meow.meowchatting.user.domain.error;

import org.springframework.http.HttpStatus;

import com.meow.meowchatting.common.exception.MeowCode;

import lombok.Getter;

@Getter
public enum MeowUserCode implements MeowCode {

	MEOW_USER_NOT_FOUND(1000, "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND); // TODO : 도메인 별 responseCode 범위 설정하기

	private final int responseCode;

	private final String responseMessage;

	private final HttpStatus httpStatus;

	MeowUserCode(int responseCode, String responseMessage, HttpStatus httpStatus) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.httpStatus = httpStatus;
	}

}
