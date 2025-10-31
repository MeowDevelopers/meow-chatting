package com.meow.meowchatting.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.meow.meowchatting.websocket.interceptor.JwtHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final WebSocketHandler webSocketHandler;

	private final JwtHandshakeInterceptor jwtHandshakeInterceptor;

	public WebSocketConfig(WebSocketHandler webSocketHandler, JwtHandshakeInterceptor jwtHandshakeInterceptor) {
		this.webSocketHandler = webSocketHandler;
		this.jwtHandshakeInterceptor = jwtHandshakeInterceptor;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws/chat")
			.addInterceptors(jwtHandshakeInterceptor)
			.setAllowedOrigins("*");
	}

}
