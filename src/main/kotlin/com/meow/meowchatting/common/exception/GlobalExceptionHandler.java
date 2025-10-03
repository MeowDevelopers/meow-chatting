package com.meow.meowchatting.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meow.meowchatting.common.response.DataResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Base Exception
	 */
	@ExceptionHandler(MeowException.class)
	public ResponseEntity<DataResponse<String>> meowException(MeowException e) {
		log.error("Error : ", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResponse<>(e.getMeowCode()));
	}

}
