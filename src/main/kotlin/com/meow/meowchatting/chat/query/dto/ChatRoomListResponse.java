package com.meow.meowchatting.chat.query.dto;

import com.meow.meowchatting.chat.command.enums.RoomType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomListResponse {

    private Long roomId;
    private RoomType roomType;
    private String roomName;
    private Long roomOwnerUserId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @QueryProjection
    public ChatRoomListResponse(Long roomId, RoomType roomType, String roomName, Long roomOwnerUserId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomName = roomName;
        this.roomOwnerUserId = roomOwnerUserId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
