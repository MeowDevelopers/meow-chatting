package com.meow.meowchatting.common.exception;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.meow.meowchatting.common.response.DataResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Base Exception
	 */
	@ExceptionHandler(MeowException.class)
	public ResponseEntity<DataResponse<Void>> handleMeowException(MeowException e) {
		return ResponseEntity.status(e.getMeowCode().getHttpStatus()).body(new DataResponse<>(e.getMeowCode()));
	}

	/**
	 * 매개변수 유효성 검증 실패 (@NotNull, @NotBlank 등)
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DataResponse<String> handleValidationException(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();

		// @NotBlank, @NotNull, @Size, @Pattern 어노테이션 순으로 유효성 검사 체크
		List<String> errorCase = List.of("NotBlank", "NotNull", "Size", "Pattern");

		List<FieldError> fieldErrors = errorCase.stream()
			.flatMap(errorCode -> bindingResult.getFieldErrors()
				.stream()
				.filter(error -> Objects.equals(error.getCode(), errorCode)))
			.toList();

		String message = (!fieldErrors.isEmpty()) ? fieldErrors.get(0).getDefaultMessage() :
			bindingResult.getFieldError().getField() + "의 형식이 올바르지 않습니다.";

		return new DataResponse<>(CommonResponseCode.VALIDATION_FAILED, message);
	}

	/**
	 * PathVariable 혹은 RequestParam 타입 불일치 예외
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DataResponse<String> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
		return new DataResponse<>(CommonResponseCode.TYPE_MISMATCH, e.getValue().toString());
	}

	/**
	 * 처리되지 않은 모든 예외
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DataResponse<String>> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResponse<>(CommonResponseCode.INTERNAL_SERVER_ERROR, e.getMessage()));
	}

}
