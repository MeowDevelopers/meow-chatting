package com.meow.meowchatting.common.exception;

import org.springframework.http.HttpStatus;

/**
 * [Meow Code 명명 규칙]
 * - 클래스 : {도메인명}ResponseCode
 * - Code : {코드로 표현하고 싶은 상태}
 */
public interface MeowCode {

	int getResponseCode();

	String getResponseMessage();

	HttpStatus getHttpStatus();

}
