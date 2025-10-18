package com.meow.meowchatting.chat.command.dto;

import com.meow.meowchatting.chat.command.enums.RoomType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoomCreateResponse {

    private Long roomId;
    private RoomType roomType;
    private String roomName;
    private String previewMessage;
    private Integer unreadMessageCount;
    private String createdFormatted;
    private List<MemberInfo> members;

    @Builder
    public ChatRoomCreateResponse(Long roomId, RoomType roomType, String roomName,
                                   String previewMessage, Integer unreadMessageCount,
                                   String createdFormatted, List<MemberInfo> members) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomName = roomName;
        this.previewMessage = previewMessage;
        this.unreadMessageCount = unreadMessageCount;
        this.createdFormatted = createdFormatted;
        this.members = members;
    }

    @Getter
    @Builder
    public static class MemberInfo {
        private Long userId;
        private String userName;
        private String profileUrl;

        public MemberInfo(Long userId, String userName, String profileUrl) {
            this.userId = userId;
            this.userName = userName;
            this.profileUrl = profileUrl;
        }
    }

    public static ChatRoomCreateResponse of(Long roomId, RoomType roomType, String roomName,
                                             String createdFormatted, List<MemberInfo> members) {
        StringBuilder previewMessageBuilder = new StringBuilder();
        members.forEach(it-> previewMessageBuilder.append(it.userName).append("/") );
        return ChatRoomCreateResponse.builder()
                .roomId(roomId)
                .roomType(roomType)
                .roomName(roomName)
                .previewMessage(previewMessageBuilder.toString())
                .unreadMessageCount(0)
                .createdFormatted(createdFormatted)
                .members(members)
                .build();
    }
}
