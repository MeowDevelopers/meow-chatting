package com.meow.meowchatting.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.slf4j.LoggerFactory

@Component
class JwtAuthenticationFilter (
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    val TOKEN_PREFIX: String = "Bearer "

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader("Authorization")
        val token = getAccessToken(header)

        val verifiedToken: String? =
            token?.takeIf { jwtProvider.validateToken(it) && SecurityContextHolder.getContext().authentication == null }

        if (verifiedToken != null) {
            val userName = jwtProvider.getUsername(verifiedToken)
            val authorities = jwtProvider.getAuthorities(verifiedToken)

            val userDetails = User(
                userName,
                "",
                authorities
            )

            val auth = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            auth.details = WebAuthenticationDetailsSource().buildDetails(req)
            SecurityContextHolder.getContext().authentication = auth

            val ip = req.remoteAddr
            val userAgent = req.getHeader("User-Agent")
            log.info(
                "JWT 인증 성공: userId={}, ip={}, userAgent={}",
                userName,
                ip,
                userAgent
            )
        }

        chain.doFilter(req, res)
    }

    private fun getAccessToken(authorizationHeader: String?): String? {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length)
        }

        return null
    }
}
