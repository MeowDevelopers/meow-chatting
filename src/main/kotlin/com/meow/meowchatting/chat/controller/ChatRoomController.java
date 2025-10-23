package com.meow.meowchatting.chat.controller;

import com.meow.meowchatting.chat.command.dto.ChatRoomCreateRequest;
import com.meow.meowchatting.chat.command.dto.ChatRoomCreateResponse;
import com.meow.meowchatting.chat.command.service.ChatRoomCreateService;
import com.meow.meowchatting.chat.command.service.ChatRoomDeleteService;
import com.meow.meowchatting.common.annotation.CurrentUser;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomCreateService chatRoomCreateService;
    private final ChatRoomDeleteService chatRoomDeleteService;

    @PostMapping("/chat/rooms")
    public DataResponse<ChatRoomCreateResponse> createChatRoom(
            @CurrentUser UserPrincipal userPrincipal,
            @Valid @RequestBody ChatRoomCreateRequest request) {

        return chatRoomCreateService.createChatRoom(userPrincipal.getUser().getId(), request);
    }

    @DeleteMapping("/user/chat/rooms/{roomId}")
    public DataResponse<Void> deleteChatRoom(
            @CurrentUser UserPrincipal userPrincipal,
            @PathVariable Long roomId) {

        return chatRoomDeleteService.deleteChatRoom(userPrincipal.getUser().getId(), roomId);
    }
}
