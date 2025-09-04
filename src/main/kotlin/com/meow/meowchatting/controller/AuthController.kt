package com.meow.meowchatting.controller

import com.meow.meowchatting.jwt.JwtProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*

data class LoginRequest(val username: String, val password: String)
data class TokenResponse(val accessToken: String, val tokenType: String = "Bearer")

@RestController
@RequestMapping("/api")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtProvider: JwtProvider
) {
    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): TokenResponse {
        try {
            // 1) 사용자 인증
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(req.username, req.password)
            )

            // 2) 인증 결과의 권한을 roles 클레임에 담을 형태로 변환
            //    (JwtProvider.generateToken(username, roles: List<Any>) 시그니처에 맞춤)
            val roles: List<Any> = authentication.authorities
                .map { it.authority }               // e.g. "ROLE_USER"
                .toList()

            // 3) JWT 발급
            val token = jwtProvider.generateToken(
                username = req.username,
                roles = roles
            )

            // 4) 응답
            return TokenResponse(accessToken = token)
        } catch (e: AuthenticationException) {
            // 스프링 시큐리티가 적절히 401 처리하도록 예외 전파
            throw e
        }
    }
}