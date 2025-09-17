package com.meow.meowchatting.service.user.service

import com.meow.meowchatting.service.oauth.AuthCodeRequestUrlProviderComposite
import com.meow.meowchatting.service.user.enums.OauthServerType
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val authCodeRequestUrlProviderComposite: AuthCodeRequestUrlProviderComposite
) {

    fun getAuthCodeRequestUrl(oathServerType: OauthServerType) : String {
        return authCodeRequestUrlProviderComposite.provide(oathServerType );
    }
}
