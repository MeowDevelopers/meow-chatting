package com.meow.meowchatting.user.command.controller;

import com.meow.meowchatting.common.annotation.CurrentUser;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import com.meow.meowchatting.user.command.service.UserCommandService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/user", name = "유저 정보 수정")
public class UserProfileCommandController {

    private final UserCommandService userCommandService;

    public UserProfileCommandController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PatchMapping(value = "/profile" , name = "유저 프로필 수정")
    public DataResponse<String> updateUserProfile(@CurrentUser UserPrincipal userPrincipal,
                                                  @RequestPart MultipartFile userProfile){
        return userCommandService.updateUserProfile(userPrincipal.getUser().getId(), userProfile);
    }
}
