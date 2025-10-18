package com.meow.meowchatting.user.query.controller;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meow.meowchatting.common.annotation.CurrentUser;
import com.meow.meowchatting.common.dto.PagingRequest;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import com.meow.meowchatting.user.query.dto.FriendListResponse;
import com.meow.meowchatting.user.query.service.FriendQueryService;

import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(value = "/api/v1/friends", name = "친구 Query 컨트롤러")
public class FriendQueryController {

	private final FriendQueryService friendQueryService;

	public FriendQueryController(FriendQueryService friendQueryService) {
		this.friendQueryService = friendQueryService;
	}

	@GetMapping(value = "/list", name = "친구 목록 조회")
	public DataResponse<Page<FriendListResponse>> friendList(@CurrentUser UserPrincipal userPrincipal,
		@RequestParam @Min(value = 1, message = "페이지는 최소 1 이상입니다.") int page,
		@RequestParam @Min(value = 1, message = "데이터 수는 최소 1 이상입니다.") int size) {
		PagingRequest request = PagingRequest.of(page, size);
		return friendQueryService.friendList(userPrincipal.getUser().getId(), request);
	}

}
