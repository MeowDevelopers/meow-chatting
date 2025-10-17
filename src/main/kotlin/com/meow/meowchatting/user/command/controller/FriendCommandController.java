package com.meow.meowchatting.user.command.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meow.meowchatting.common.annotation.CurrentUser;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import com.meow.meowchatting.user.command.dto.FriendNameUpdateRequest;
import com.meow.meowchatting.user.command.dto.FriendNameUpdateResponse;
import com.meow.meowchatting.user.command.service.FriendCommandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/friend", name = "친구 Command 컨트롤러")
public class FriendCommandController {

	private final FriendCommandService friendCommandService;

	public FriendCommandController(FriendCommandService friendCommandService) {
		this.friendCommandService = friendCommandService;
	}

	@PutMapping(name = "친구 이름 변경")
	public DataResponse<FriendNameUpdateResponse> updateFriendName(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody FriendNameUpdateRequest request) {
		return friendCommandService.updateFriendName(userPrincipal.getUser().getId(), request);
	}
}
