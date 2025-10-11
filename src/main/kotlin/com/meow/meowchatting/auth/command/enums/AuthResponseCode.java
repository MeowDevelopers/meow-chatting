package com.meow.meowchatting.auth.command.enums;

import com.meow.meowchatting.common.exception.MeowCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public enum AuthResponseCode implements MeowCode {
    LOGIN_SUCCESS("로그인 완료", HttpStatus.OK),
    OAUTH_AUTHORIZE_SUCCESS("인가 코드 정상처리", HttpStatus.OK),
    NOT_FOUND_PROVIDER_TYPE("잘못된 요청입니다.", HttpStatus.NOT_FOUND),
    INVALID_AUTH_CODE("유효하지 않은 인가 코드입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("유효하지 않은 엑세스 토큰입니다.", HttpStatus.UNAUTHORIZED),
    OAUTH_CONFIG_ERROR("OAuth 설정값이 누락되었습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_AUTH_URI("잘못된 인가 요청 URI입니다.", HttpStatus.BAD_REQUEST);


    private final String responseMessage;
    private final HttpStatus httpStatus;

    AuthResponseCode(String responseMessage, HttpStatus httpStatus){
        this.responseMessage = responseMessage;
        this.httpStatus = httpStatus;
    }


    @Override
    public int getResponseCode() {
        return httpStatus.value();
    }

    @Override
    public String getResponseMessage() {
        return responseMessage;
    }

    @Override
    @NotNull
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
