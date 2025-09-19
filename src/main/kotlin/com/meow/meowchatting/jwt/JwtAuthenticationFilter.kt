package com.meow.meowchatting.jwt

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource

@Component
class JwtAuthenticationFilter (
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader("Authorization")
        val token = jwtProvider.getAccessToken(header)

        if (token.isNullOrBlank()) {
            chain.doFilter(req, res)
            return
        }

        try {
            val userName = jwtProvider.getUsername(token)
            val authorities = jwtProvider.getAuthorities(token)

            val userDetails = User(userName, "", authorities)
            val auth = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).apply {
                details = WebAuthenticationDetailsSource().buildDetails(req)
            }

            SecurityContextHolder.getContext().authentication = auth

            log.info(
                "JWT 인증 성공: userName={}, method={}, endpoint={}, userAgent={}",
                userName, req.method, req.requestURI, req.getHeader("User-Agent")
            )

            chain.doFilter(req, res)
        } catch (e: ExpiredJwtException) {
            SecurityContextHolder.clearContext()
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired")
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
        }
    }
}
