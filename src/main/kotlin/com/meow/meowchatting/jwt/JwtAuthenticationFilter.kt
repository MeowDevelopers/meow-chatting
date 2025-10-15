package com.meow.meowchatting.jwt

import com.meow.meowchatting.common.exception.MeowException
import com.meow.meowchatting.jwt.error.JwtResponseCode
import com.meow.meowchatting.user.query.service.UserPrincipalQueryService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val jwtProvider: JwtProvider,
    private val userPrincipalQueryService: UserPrincipalQueryService
) : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader("Authorization")
        val token = jwtProvider.getAccessToken(header)

        if (token.isNullOrBlank()) {
            chain.doFilter(request, response)
            return
        }

        try {
            validateToken(token)

            val userDetails = getUserPrincipal(token)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = authentication

            log.info(
                "JWT 인증 성공: method={}, endpoint={}, userAgent={}",
                request.method, request.requestURI, request.getHeader("User-Agent")
            )

            chain.doFilter(request, response)
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
        }
    }

    private fun validateToken(token: String) {
        if (!jwtProvider.validateToken(token)) {
            throw MeowException(JwtResponseCode.EXPIRED)
        }
    }

    private fun getUserPrincipal(token: String): UserDetails {
        val claims = jwtProvider.getClaims(token)
        val userId = claims.subject.toLong()

        return userPrincipalQueryService.loadUserByUserId(userId, claims)
    }
}
