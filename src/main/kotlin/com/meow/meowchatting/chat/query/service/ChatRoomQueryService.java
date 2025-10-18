package com.meow.meowchatting.chat.query.service;

import com.meow.meowchatting.chat.command.domain.error.RoomResponseCode;
import com.meow.meowchatting.chat.query.dto.ChatRoomListResponse;
import com.meow.meowchatting.chat.query.repository.RoomPagedQueryRepository;
import com.meow.meowchatting.common.dto.PagingRequest;
import com.meow.meowchatting.common.response.DataResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ChatRoomQueryService {

    private final RoomPagedQueryRepository roomPagedQueryRepository;

    public ChatRoomQueryService(RoomPagedQueryRepository roomPagedQueryRepository) {
        this.roomPagedQueryRepository = roomPagedQueryRepository;
    }

    /**
     * 채팅방 목록 조회
     */
    public DataResponse<Page<ChatRoomListResponse>> chatRoomList(Long userId, PagingRequest request) {
        Page<ChatRoomListResponse> chatRoomList = roomPagedQueryRepository.pagedChatRoomList(userId, request);
        return new DataResponse<>(RoomResponseCode.SUCCESS, chatRoomList);
    }

}
