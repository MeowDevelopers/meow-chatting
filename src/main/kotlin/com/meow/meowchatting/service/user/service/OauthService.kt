package com.meow.meowchatting.service.user.service

import com.meow.meowchatting.service.oauth.AuthCodeRequestUrlProviderComposite
import com.meow.meowchatting.service.oauth.OauthMemberClientComposite
import com.meow.meowchatting.service.user.domain.User
import com.meow.meowchatting.service.user.enums.OauthServerType
import org.springframework.stereotype.Service

@Service
class OauthService(
    private val authCodeRequestUrlProviderComposite: AuthCodeRequestUrlProviderComposite,
    private val oauthMemberClientComposite: OauthMemberClientComposite
) {

    fun getAuthCodeRequestUrl(oathServerType: OauthServerType) : String {
        return authCodeRequestUrlProviderComposite.provide(oathServerType );
    }

//    fun login(oathServerType: OauthServerType, code: String) : Long {
//        val user = oauthMemberClientComposite.requestAuthorizationAndAccessToken(oathServerType, code)
//    }
}
