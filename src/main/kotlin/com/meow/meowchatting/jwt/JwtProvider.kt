package com.meow.meowchatting.jwt

import com.meow.meowchatting.user.command.enums.UserType
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration

import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtProvider (
    @Value("\${jwt.secret-key}") private val secret: String,
    @Value("\${jwt.access.expiration}") private val accessExpiration: Long,
    @Value("\${jwt.refresh.expiration}") private val refreshExpiration: Duration
) {
    private val key: SecretKey by lazy { Keys.hmacShaKeyFor(secret.toByteArray()) }

    fun generateAccessToken(userId: Long, role: UserType): String {
        val now = Date().time
        val expiresAt = Date(now + accessExpiration * 1000)

        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(Date(now))
            .expiration(expiresAt)
            .claim("roles", role.name)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun generateRefreshToken(userId: Long, role: UserType): String {
        val now = Date().time
        val expiresAt = Date(now + refreshExpiration.toMillis())

        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(Date(now))
            .expiration(expiresAt)
            .claim("roles", role.name)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean = try {
        val claims = Jwts.parser().verifyWith(key).build()
            .parseSignedClaims(token).payload
        claims.expiration.after(Date())
    } catch (_: Exception) { false }

    fun getAccessToken(authorizationHeader: String?): String? {
        val prefix = "Bearer "

        if (authorizationHeader.isNullOrBlank() || !authorizationHeader.startsWith(prefix, ignoreCase = true)) {
            return null
        }

        val token = authorizationHeader.substring(prefix.length).trim()
        if (token.isBlank()) return null

        return token
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
