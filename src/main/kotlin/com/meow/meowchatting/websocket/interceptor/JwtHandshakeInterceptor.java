package com.meow.meowchatting.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.meow.meowchatting.jwt.JwtProvider;
import com.meow.meowchatting.user.query.service.UserPrincipalQueryService;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

	private final JwtProvider jwtProvider;

	private final UserPrincipalQueryService userPrincipalQueryService;

	public JwtHandshakeInterceptor(JwtProvider jwtProvider,
		UserPrincipalQueryService userPrincipalQueryService) {
		this.jwtProvider = jwtProvider;
		this.userPrincipalQueryService = userPrincipalQueryService;
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
		Map<String, Object> attributes) {
		try {
			String query = request.getURI().getQuery();
			if (query == null || !query.startsWith("token=")) {
				log.debug("WebSocket 연결 실패 : 토큰은 필수입니다.");
				return false;
			}

			String token = query.substring(6); // "token=" 제거

			if (!jwtProvider.validateToken(token)) {
				log.debug("WebSocket 연결 실패 : 유효하지 않은 토큰입니다.");
				return false;
			}

			Claims claims = jwtProvider.getClaims(token);
			Long userId = Long.parseLong(claims.getSubject());

			UserDetails userDetails = userPrincipalQueryService.loadUserByUserId(userId, claims);

			attributes.put("userId", userId);
			attributes.put("userPrincipal", userDetails);
			attributes.put("token", token);

			log.debug("WebSocket 연결 성공 : userId={}", userId);
			return true;
		} catch (Exception e) {
			log.error("WebSocket handshake token 처리 중 실패", e);
			return false;
		}
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
		WebSocketHandler wsHandler, Exception exception) {
		if (exception != null) {
			log.error("WebSocket handshake 후 에러 발생", exception);
		}
	}

}
