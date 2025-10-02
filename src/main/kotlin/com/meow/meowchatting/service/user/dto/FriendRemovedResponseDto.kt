package com.meow.meowchatting.service.user.dto

data class FriendRemovedResponseDto(
    var userId: Long,
    var friendName: String,
    var friendStatus: String,
    var friendStatusMessage: String
)
