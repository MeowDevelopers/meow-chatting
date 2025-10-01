package com.meow.meowchatting.service.user.dto

data class FriendRemoveRequest(
    val userId: Long,
    val friendId: Long,
    val isHardDelete: Boolean
)
