package com.meow.meowchatting.service.oauth.kakao

import com.meow.meowchatting.service.oauth.AuthCodeRequestUrlProvider
import com.meow.meowchatting.service.user.enums.OauthServerType
import org.springframework.web.util.UriComponentsBuilder

class KakaoAuthCodeRequestUrlProvider(
    private val kakaoOauthProviderConfig: KakaoOauthProviderConfig,
    private val kakaoOauthRegistrationConfig: KakaoOauthRegistrationConfig
) : AuthCodeRequestUrlProvider {

    override fun supportServer() : OauthServerType {
        return OauthServerType.KAKAO
    }

    override fun provide(): String {
        return UriComponentsBuilder
            .fromUriString(kakaoOauthProviderConfig.authorizationUri)
            .queryParam("response_type", "code")
            .queryParam("client_id", kakaoOauthRegistrationConfig.clientId)
            .queryParam("redirect_uri", kakaoOauthRegistrationConfig.redirectUri)
            .queryParam("scope", kakaoOauthRegistrationConfig.scope.joinToString(","))
            .toUriString()
    }
}
