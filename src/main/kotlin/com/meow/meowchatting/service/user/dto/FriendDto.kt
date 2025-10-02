package com.meow.meowchatting.service.user.dto

data class FriendDto(
    var userId: Long,
    var friendName: String,
    var profileUrl: String,
    val friendStatus: String
)
