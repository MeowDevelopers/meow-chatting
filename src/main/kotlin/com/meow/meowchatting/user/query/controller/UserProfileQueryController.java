package com.meow.meowchatting.user.query.controller;

import com.meow.meowchatting.common.annotation.CurrentUser;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import com.meow.meowchatting.user.query.dto.UserQueryProfileResponse;
import com.meow.meowchatting.user.query.service.UserProfileQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user", name = "유저 정보 조회")
public class UserProfileQueryController {
    private final UserProfileQueryService userProfileQueryService;

    public UserProfileQueryController(UserProfileQueryService userProfileQueryService) {
        this.userProfileQueryService = userProfileQueryService;
    }

    @GetMapping(value = "/profile" ,name = "유저 프로필 조회")
    public DataResponse<UserQueryProfileResponse> getUserProfile(@CurrentUser UserPrincipal userPrincipal){
        return userProfileQueryService.getUserProfile(userPrincipal.getUser().getId());
    }
}
