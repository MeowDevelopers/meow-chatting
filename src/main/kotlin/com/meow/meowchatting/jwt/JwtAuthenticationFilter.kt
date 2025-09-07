package com.meow.meowchatting.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationFilter (
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader("Authorization")
        val token = if (header?.startsWith("Bearer ") == true) header.substring(7) else null

        if (token != null
                && jwtProvider.validateToken(token)
                && SecurityContextHolder.getContext().authentication == null
            ) {
            val userName = jwtProvider.getUsername(token)
            val authorities = jwtProvider.getAuthorities(token)
            
            val userDetails = User(
                userName,
                "",
                authorities
            )

            val auth = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            auth.details = WebAuthenticationDetailsSource().buildDetails(req)
            SecurityContextHolder.getContext().authentication = auth
        }

        chain.doFilter(req, res)
    }
}
