package com.meow.meowchatting.s3.enums;

import com.meow.meowchatting.common.exception.MeowCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public enum S3UploadCode implements MeowCode {
    AWS_S3_UPLOAD_FAIL("S3 업로드에 실패했습니다.", HttpStatus.BAD_REQUEST);

    private final String responseMessage;
    private final HttpStatus httpStatus;

    S3UploadCode(String responseMessage, HttpStatus httpStatus) {
        this.responseMessage = responseMessage;
        this.httpStatus = httpStatus;
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
