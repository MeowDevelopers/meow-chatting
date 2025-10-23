package com.meow.meowchatting.chat.command.dto;

import com.meow.meowchatting.chat.command.enums.RoomType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomCreateRequest {

    @NotNull(message = "채팅방 타입은 필수입니다.")
    private RoomType roomType;

    @NotBlank(message = "채팅방 이름은 필수입니다.")
    @Length(max = 100, message = "채팅방 이름은 최대 100자 이하입니다.")
    private String roomName;

    @Valid
    @NotNull(message = "채팅방 멤버는 필수입니다.")
    private List<MemberInfo> members;

    @Getter
    @NoArgsConstructor
    public static class MemberInfo {

        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;

        @NotBlank(message = "사용자 이름은 필수입니다.")
        private String userName;

        private String profileUrl;
    }
}
