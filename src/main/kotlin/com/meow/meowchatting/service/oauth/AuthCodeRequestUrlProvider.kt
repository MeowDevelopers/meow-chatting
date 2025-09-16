package com.meow.meowchatting.service.oauth

import com.meow.meowchatting.service.user.enums.OauthServerType

interface AuthCodeRequestUrlProvider {

    fun supportServer(): OauthServerType

    fun provide(): String

}
