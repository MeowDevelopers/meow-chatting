package com.meow.meowchatting.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtProvider (
    @Value("\${jwt.secret-key}") private val secret: String,
    @Value("\${jwt.access.expiration}") private val accessExpiration: Long,
    @Value("\${jwt.refresh.expiration}") private val refreshExpiration: Long
) {
    private val key: SecretKey by lazy { Keys.hmacShaKeyFor(secret.toByteArray()) }
    private val zone: ZoneId = ZoneId.of("Asia/Seoul")

    fun generateToken(username: String, roles: List<Any> = emptyList()): String {
        val now = Instant.now()
        val expiresAt  = now.plusSeconds(accessExpiration)

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiresAt))
            .claim("roles", roles)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateRefreshToken(username: String, roles: List<String>): String {
        val nowLdt = LocalDateTime.now(zone)
        val expiresAtLdt  = nowLdt.plusSeconds(refreshExpiration)

        val now = Date.from(nowLdt.atZone(zone).toInstant())
        val expiresAt = Date.from(expiresAtLdt.atZone(zone).toInstant())

        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(expiresAt)
            .claim("roles", roles)
            .signWith(key)
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
}
