package com.meow.meowchatting.service.oauth

import com.fasterxml.jackson.core.JsonProcessingException
import com.meow.meowchatting.service.user.domain.User
import com.meow.meowchatting.service.user.enums.OauthServerType


interface OauthMemberClient {
    fun supportServer(): OauthServerType

    @Throws(JsonProcessingException::class)
    fun requestAuthorizationAndAccessToken(code: String): User
}
