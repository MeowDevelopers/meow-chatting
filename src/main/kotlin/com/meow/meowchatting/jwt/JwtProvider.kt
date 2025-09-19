package com.meow.meowchatting.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtProvider (
    @Value("\${jwt.secret-key}") private val secret: String,
    @Value("\${jwt.access.expiration}") private val accessExpiration: Long,
    @Value("\${jwt.refresh.expiration}") private val refreshExpiration: Duration
) {
    private val key: SecretKey by lazy { Keys.hmacShaKeyFor(secret.toByteArray()) }

    fun generateToken(username: String, roles: List<Any> = emptyList()): String {
        val now = Date().time
        val expiresAt = Date(now + accessExpiration * 1000)

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date(now))
            .expiration(expiresAt)
            .claim("roles", roles)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateRefreshToken(username: String, roles: List<String>): String {
        val now = Date().time
        val expiresAt = Date(now + refreshExpiration.toMillis())

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date(now))
            .expiration(expiresAt)
            .claim("roles", roles)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean = try {
        val claims = Jwts.parser().verifyWith(key).build()
            .parseSignedClaims(token).payload
        claims.expiration.after(Date())
    } catch (_: Exception) { false }

    fun getUsername(token: String): String =
        Jwts.parser().verifyWith(key).build()
            .parseSignedClaims(token).payload.subject

    fun getAuthorities(token: String): List<SimpleGrantedAuthority> {
        val claims = Jwts.parser().verifyWith(key).build()
            .parseSignedClaims(token).payload

        val roles = claims["roles"] as? List<*> ?: emptyList<Any>()
        return roles.map { SimpleGrantedAuthority(it.toString()) }
    }

    fun getAccessToken(authorizationHeader: String?): String? {
        val prefix = "Bearer "

        if (authorizationHeader.isNullOrBlank() || !authorizationHeader.startsWith(prefix, ignoreCase = true)) {
            return null
        }

        val token = authorizationHeader.substring(prefix.length).trim()
        if (token.isBlank()) return null

        return if (validateToken(token)) token else null
    }
}
