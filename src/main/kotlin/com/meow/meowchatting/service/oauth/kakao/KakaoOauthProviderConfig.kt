package com.meow.meowchatting.service.oauth.kakao

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.kakao")
data class KakaoOauthProviderConfig(
    val authorizationUri: String,
    val tokenUri: String,
    val userInfoUri: String,
    val userNameAttribute: String
) {
}
