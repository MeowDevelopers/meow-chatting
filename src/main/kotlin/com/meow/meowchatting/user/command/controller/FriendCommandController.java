package com.meow.meowchatting.user.command.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping(value = "/api/v1/friends", name = "친구 Command 컨트롤러")
public class FriendCommandController {

	private final FriendCommandService friendCommandService;

	public FriendCommandController(FriendCommandService friendCommandService) {
		this.friendCommandService = friendCommandService;
	}

	@PutMapping(name = "친구 이름 변경")
	public DataResponse<FriendNameUpdateResponse> updateFriendName(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody FriendNameUpdateRequest request) {
		return friendCommandService.updateFriendName(userPrincipal.getUser().getId(), request);
	}

	@DeleteMapping(value = "/{friendId}",name = "친구 삭제")
	public DataResponse<Void> deleteFriend(@CurrentUser UserPrincipal userPrincipal, @PathVariable @NotNull(message = "친구 ID는 필수입니다.") Long friendId) {
		return friendCommandService.deleteFriend(userPrincipal.getUser().getId(), friendId);
	}
}
