package com.meow.meowchatting.service.user.dto

data class RenameFriendResponse(
    var userId: Long,
    var userProfile: String,
    var userName: String
)
