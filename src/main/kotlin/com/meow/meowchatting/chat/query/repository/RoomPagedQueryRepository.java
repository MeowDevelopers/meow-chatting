package com.meow.meowchatting.chat.query.repository;

import com.meow.meowchatting.chat.query.dto.ChatRoomListResponse;
import com.meow.meowchatting.common.dto.PagingRequest;
import org.springframework.data.domain.Page;

public interface RoomPagedQueryRepository {

    Page<ChatRoomListResponse> pagedChatRoomList(Long userId, PagingRequest request);

}
