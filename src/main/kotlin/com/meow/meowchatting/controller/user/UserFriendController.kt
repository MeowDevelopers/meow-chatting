package com.meow.meowchatting.controller.user

import com.meow.meowchatting.service.user.dto.FriendDto
import com.meow.meowchatting.service.user.dto.FriendRemoveRequest
import com.meow.meowchatting.service.user.dto.FriendRemovedResponseDto
import com.meow.meowchatting.service.user.service.UserFriendService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/friends")
class UserFriendController(
    val userFriendService: UserFriendService
){

    @GetMapping(name = "친구 목록 조회")
    fun getFriendList(@RequestParam userId: Long,
                      @RequestParam(required = false, defaultValue = "0") page: Int,
                      @RequestParam(required = false, defaultValue = "20") size: Int): Page<FriendDto> {
        return userFriendService.getUserFriends(userId, PageRequest.of(page, size))
    }

    @PatchMapping(name = "친구 이름 변경")
    fun patchFriendName(): String {
        userFriendService.modFriendName()
        return ""
    }

    @DeleteMapping(name = "친구 삭제")
    fun deleteFriend(@Valid @RequestBody request: FriendRemoveRequest): ResponseEntity<FriendRemovedResponseDto> {
        val deleteResult = userFriendService.deleteUserFriend(request.userId, request.friendId, request.isHardDelete)

        return ResponseEntity.ok(deleteResult)
    }
}
