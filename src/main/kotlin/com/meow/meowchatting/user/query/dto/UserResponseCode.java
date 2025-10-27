package com.meow.meowchatting.user.query.dto;

import com.meow.meowchatting.common.exception.MeowCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public enum UserResponseCode implements MeowCode {
    SUCCESS("정상 처리 완료", HttpStatus.OK),
    NOT_FOUND_USER("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND);

    private final String responseMessage;
    private final HttpStatus httpStatus;

    UserResponseCode(String responseMessage, HttpStatus httpStatus){
        this.responseMessage = responseMessage;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getResponseMessage(){
        return responseMessage;
    }

    @Override
    @NotNull
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
