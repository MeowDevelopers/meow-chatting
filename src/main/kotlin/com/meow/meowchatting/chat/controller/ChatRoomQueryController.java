package com.meow.meowchatting.chat.controller;

import com.meow.meowchatting.chat.query.dto.ChatRoomListResponse;
import com.meow.meowchatting.chat.query.service.ChatRoomQueryService;
import com.meow.meowchatting.common.annotation.CurrentUser;
import com.meow.meowchatting.common.dto.PagingRequest;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/api/v1/user/chat/rooms", name = "채팅방 Query 컨트롤러")
public class ChatRoomQueryController {

    private final ChatRoomQueryService chatRoomQueryService;

    public ChatRoomQueryController(ChatRoomQueryService chatRoomQueryService) {
        this.chatRoomQueryService = chatRoomQueryService;
    }

    @GetMapping(name = "채팅방 목록 조회")
    public DataResponse<Page<ChatRoomListResponse>> chatRoomList(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam @Min(value = 1, message = "페이지는 최소 1 이상입니다.") int page,
            @RequestParam @Min(value = 1, message = "데이터 수는 최소 1 이상입니다.") int size) {
        PagingRequest request = PagingRequest.of(page, size);
        return chatRoomQueryService.chatRoomList(userPrincipal.getUser().getId(), request);
    }

}
