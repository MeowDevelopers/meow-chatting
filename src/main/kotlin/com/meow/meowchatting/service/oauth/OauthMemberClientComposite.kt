package com.meow.meowchatting.service.oauth

import com.fasterxml.jackson.core.JsonProcessingException
import com.meow.meowchatting.service.user.domain.User
import com.meow.meowchatting.service.user.enums.OauthServerType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OauthMemberClientComposite private constructor(
    private val mapping: Map<OauthServerType, OauthMemberClient>
) {

    @Autowired
    constructor(clients: Set<OauthMemberClient>) : this(
        clients
            .also {
                // 키 중복 방지 (Java Collectors.toMap 기본 동작과 동일하게 예외)
                val dup = it.groupBy { c -> c.supportServer() }.filterValues { g -> g.size > 1 }
                require(dup.isEmpty()) { "중복된 소셜 로그인 클라이언트: ${dup.keys}" }
            }
            .associateBy { it.supportServer() }
    )

    @Throws(JsonProcessingException::class)
    fun requestAuthorizationAndAccessToken(
        oauthServerType: OauthServerType,
        authCode: String
    ): User = getClient(oauthServerType).requestAuthorizationAndAccessToken(authCode)

    private fun getClient(oauthServerType: OauthServerType): OauthMemberClient =
        requireNotNull(mapping[oauthServerType]) { "지원하지 않는 소셜 로그인 타입입니다." }
}
