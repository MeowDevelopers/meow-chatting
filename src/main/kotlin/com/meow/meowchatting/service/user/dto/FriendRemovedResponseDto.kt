package com.meow.meowchatting.service.user.dto

data class FriendRemovedResponseDto(
    val userId: Long,
    val friendName: String,
    val friendStatus: String,
    val friendStatusMessage: String
)
