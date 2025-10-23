package com.meow.meowchatting.chat.command.domain.error;

import com.meow.meowchatting.common.exception.MeowCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RoomResponseCode implements MeowCode {

    SUCCESS("정상 처리 완료", HttpStatus.OK),

    NOT_FOUND("존재하지 않는 채팅방입니다.", HttpStatus.NOT_FOUND),

    INVALID_ROOM_TYPE("지원하지 않는 채팅방 타입입니다.", HttpStatus.BAD_REQUEST),

    INVALID_OWNER("채팅방 소유자만 삭제할 수 있습니다.", HttpStatus.FORBIDDEN);

    private final String responseMessage;

    private final HttpStatus httpStatus;

    RoomResponseCode(String responseMessage, HttpStatus httpStatus) {
        this.responseMessage = responseMessage;
        this.httpStatus = httpStatus;
    }

}
