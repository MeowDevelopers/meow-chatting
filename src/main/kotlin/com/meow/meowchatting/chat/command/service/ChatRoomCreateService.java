package com.meow.meowchatting.chat.command.service;

import com.meow.meowchatting.chat.command.dto.ChatRoomCreateRequest;
import com.meow.meowchatting.chat.command.dto.ChatRoomCreateResponse;
import com.meow.meowchatting.chat.command.repository.RoomCommandRepository;
import com.meow.meowchatting.chat.command.repository.RoomUsersRepository;
import com.meow.meowchatting.chat.command.domain.Room;
import com.meow.meowchatting.chat.command.domain.RoomUsers;
import com.meow.meowchatting.chat.command.domain.error.RoomResponseCode;
import com.meow.meowchatting.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomCreateService {

    private final RoomCommandRepository roomCommandRepository;
    private final RoomUsersRepository roomUsersRepository;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("a h:mm");

    /**
     * 채팅방 생성 (DIRECT, GROUP 타입 모두 처리)
     */
    public DataResponse<ChatRoomCreateResponse> createChatRoom(Long userId, ChatRoomCreateRequest request) {
        // Room 엔티티 생성 및 저장
        Room room = Room.builder()
                .roomType(request.getRoomType())
                .roomName(request.getRoomName())
                .roomOwnerUserId(userId)
                .build();

        Room savedRoom = roomCommandRepository.save(room);

        // RoomUsers 저장 (방 생성자 포함)
        RoomUsers ownerRoomUser = RoomUsers.builder()
                .roomId(savedRoom.getId())
                .userId(userId)
                .isActive(true)
                .build();
        roomUsersRepository.save(ownerRoomUser);

        // 멤버들 RoomUsers 저장
        request.getMembers().forEach(member -> {
            RoomUsers roomUser = RoomUsers.builder()
                    .roomId(savedRoom.getId())
                    .userId(member.getUserId())
                    .isActive(true)
                    .build();
            roomUsersRepository.save(roomUser);
        });

        // Response DTO 생성
        List<ChatRoomCreateResponse.MemberInfo> members = request.getMembers().stream()
                .map(member -> ChatRoomCreateResponse.MemberInfo.builder()
                        .userId(member.getUserId())
                        .userName(member.getUserName())
                        .profileUrl(member.getProfileUrl())
                        .build())
                .collect(Collectors.toList());

        String createdFormatted = formatCreatedAt(savedRoom.getCreatedAt());

        ChatRoomCreateResponse response = ChatRoomCreateResponse.of(
                savedRoom.getId(),
                savedRoom.getRoomType(),
                savedRoom.getRoomName(),
                createdFormatted,
                members
        );

        return new DataResponse<>(RoomResponseCode.SUCCESS, response);
    }

    /**
     * 생성 시간을 "오전 10:22" 형식으로 포맷팅
     */
    private String formatCreatedAt(LocalDateTime createdAt) {
        return createdAt.format(TIME_FORMATTER);
    }
}
