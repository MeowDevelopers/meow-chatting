package com.meow.meowchatting.service.oauth

import com.meow.meowchatting.service.user.enums.OauthServerType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthCodeRequestUrlProviderComposite(
    private val mapping: Map<OauthServerType, AuthCodeRequestUrlProvider>
) {

    @Autowired
    constructor (providers: Set<AuthCodeRequestUrlProvider>) : this(
        providers.associateBy { it.supportServer() }
    )

    fun provide(oauthServerType: OauthServerType) : String { return getProvider(oauthServerType).provide() }

    private fun getProvider(oauthServerType: OauthServerType): AuthCodeRequestUrlProvider {
        return requireNotNull(mapping[oauthServerType]) { "지원하지 않는 소셜 로그인 타입입니다." }
    }
}
