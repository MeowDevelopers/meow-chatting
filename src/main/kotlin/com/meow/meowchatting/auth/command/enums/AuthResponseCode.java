package com.meow.meowchatting.auth.command.enums;

import com.meow.meowchatting.common.exception.MeowCode;
import org.springframework.http.HttpStatus;

public enum AuthResponseCode implements MeowCode {
    LOGIN_SUCCESS("로그인 완료"),
    OAUTH_AUTHORIZE_SUCCESS("인가 코드 정상처리"),
    REDIRECT_FAILED("리디렉션 실패");


    private final String responseMessage;

    AuthResponseCode(String responseMessage){
        this.responseMessage = responseMessage;
    }


    @Override
    public int getResponseCode() {
        return 0;
    }

    @Override
    public String getResponseMessage() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
