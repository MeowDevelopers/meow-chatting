package com.meow.meowchatting.service.user.dto

data class FriendDto(
    val userId: Long,
    val friendName: String,
    val profileUrl: String,
    val friendStatus: String
)
