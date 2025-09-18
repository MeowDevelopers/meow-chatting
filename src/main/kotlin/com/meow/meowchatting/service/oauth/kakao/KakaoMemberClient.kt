package com.meow.meowchatting.service.oauth.kakao

import com.google.gson.Gson
import com.meow.meowchatting.service.oauth.OauthMemberClient
import com.meow.meowchatting.service.user.domain.User
import com.meow.meowchatting.service.user.enums.OauthServerType
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

class KakaoMemberClient(
    private val kakaoApiClient: KakaoApiClient,
    private val kakaoOauthRegistrationConfig: KakaoOauthRegistrationConfig,
) : OauthMemberClient {

    override fun supportServer(): OauthServerType = OauthServerType.KAKAO

    override fun requestAuthorizationAndAccessToken(code: String): User {
        val tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(code))

        val kakaoMemberResponse =
            kakaoApiClient.fetchMember("Bearer ${tokenInfo.accessToken}")

        return kakaoMemberResponse.toDomain()
    }

    private fun tokenRequestParams(authCode: String): MultiValueMap<String, String> =
        LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "authorization_code")
            add("client_id", kakaoOauthRegistrationConfig.clientId)
            add("redirect_uri", kakaoOauthRegistrationConfig.redirectUri)
            add("code", authCode)
            add("client_secret", kakaoOauthRegistrationConfig.clientSecret)
        }
}
