package com.meow.meowchatting.service.oauth.kakao

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.kakao")
data class KakaoOauthRegistrationConfig(
    val redirectUri: String,
    val clientId: String,
    val clientSecret: String,
    val scope: Array<String>,
) {}
