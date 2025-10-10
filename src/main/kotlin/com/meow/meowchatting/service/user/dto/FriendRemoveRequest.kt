package com.meow.meowchatting.service.user.dto

data class FriendRemoveRequest(
    var userId: Long,
    var friendId: Long,
    var isHardDelete: Boolean
)
