package com.meow.meowchatting.service.user.dto

data class RenameFriendResponse(
    val userId: Long,
    val userProfile: String,
    val userName: String
)
