package com.meow.meowchatting.websocket.handler;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meow.meowchatting.chat.command.dto.WebSocketMessageRequest;
import com.meow.meowchatting.chat.command.dto.WebSocketMessageResponse;
import com.meow.meowchatting.chat.command.service.MessageCommandService;
import com.meow.meowchatting.chat.query.service.RoomUsersQueryService;
import com.meow.meowchatting.websocket.manager.WebSocketSessionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

	private final WebSocketSessionManager webSocketSessionManager;

	private final MessageCommandService messageCommandService;

	private final RoomUsersQueryService roomUsersQueryService;

	private final ObjectMapper objectMapper;

	public ChatWebSocketHandler(WebSocketSessionManager webSocketSessionManager, MessageCommandService messageCommandService,
		RoomUsersQueryService roomUsersQueryService, ObjectMapper objectMapper) {
		this.webSocketSessionManager = webSocketSessionManager;
		this.messageCommandService = messageCommandService;
		this.roomUsersQueryService = roomUsersQueryService;
		this.objectMapper = objectMapper;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Long userId = (Long) session.getAttributes().get("userId");

		if (userId == null) {
			session.close(CloseStatus.BAD_DATA);
			return;
		}

		webSocketSessionManager.addSession(userId, session);
		log.debug("WebSocket 연결 수립 성공 : userId={}", userId);

		sendMessage(session, WebSocketMessageResponse.ofResult("WebSocket 연결이 수립되었습니다."));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		Long userId = (Long) session.getAttributes().get("userId");
		if (userId == null) {
			log.debug("유효하지 않은 세션입니다.");
			return;
		}

		try {
			WebSocketMessageRequest request = objectMapper.readValue(message.getPayload(), WebSocketMessageRequest.class);

			switch (request.getCommand()) {
				case SUBSCRIBE -> handleSubscribe(userId, request.getRoomId(), session);
				case UNSUBSCRIBE -> handleUnsubscribe(userId, request.getRoomId(), session);
				case SEND -> handleSendMessage(userId, request, session);
				default -> sendErrorMessage(session, "알 수 없는 명령어입니다.");
			}
		} catch (Exception e) {
			log.error("메시지 처리 중 오류 발생 : userId={}", userId, e);
			sendErrorMessage(session, "메시지 처리에 실패했습니다.");
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		Long userId = (Long) session.getAttributes().get("userId");
		if (userId != null) {
			webSocketSessionManager.removeSession(userId);
			log.debug("WebSocket 연결 종료 : userId={}, status={}", userId, status);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		Long userId = (Long) session.getAttributes().get("userId");
		log.error("WebSocket 전송 오류 : userId={}", userId, exception);

		if (session.isOpen()) {
			session.close(CloseStatus.SERVER_ERROR);
		}
	}

	/**
	 * 채팅방 구독
	 */
	private void handleSubscribe(Long userId, Long roomId, WebSocketSession session) {
		if (roomId == null) {
			sendErrorMessage(session, "채팅방 ID는 필수입니다.");
			return;
		}

		try {
			roomUsersQueryService.validateRoomUsers(userId, roomId);
			webSocketSessionManager.subscribeRoom(userId, roomId);

			sendMessage(session, WebSocketMessageResponse.ofRoomAction(roomId, "채팅방 " + roomId + " 구독 완료"));

			log.debug("채팅방 구독 완료 : userId={}, roomId={}", userId, roomId);
		} catch (Exception e) {
			log.error("채팅방 구독 실패 : userId={}, roomId={}", userId, roomId, e);
			sendErrorMessage(session, "채팅방 구독에 실패했습니다.");
		}
	}

	/**
	 * 채팅방 구독 해제
	 */
	private void handleUnsubscribe(Long userId, Long roomId, WebSocketSession session) {
		webSocketSessionManager.unsubscribeRoom(userId, roomId);

		sendMessage(session, WebSocketMessageResponse.ofRoomAction(roomId, "채팅방 " + roomId + " 구독 해제 완료"));

		log.debug("채팅방 구독 해제 완료 : userId={}, roomId={}", userId, roomId);
	}

	/**
	 * 메시지 전송 처리
	 */
	private void handleSendMessage(Long userId, WebSocketMessageRequest request, WebSocketSession session) {
		if (request.getRoomId() == null) {
			sendErrorMessage(session, "채팅방 ID는 필수입니다.");
			return;
		}

		try {
			if (!webSocketSessionManager.isSubscribed(userId, request.getRoomId())) {
				sendErrorMessage(session, "구독하지 않은 채팅방입니다.");
				return;
			}

			WebSocketMessageResponse response = messageCommandService.sendMessage(
				userId,
				request.getRoomId(),
				request.getMessageType(),
				request.getMessageText(),
				request.getMessageUrl()
			);

			broadcastToRoom(request.getRoomId(), response);

			log.info("메시지 전송 완료 : userId={}, roomId={}, messageId={}",
				userId, request.getRoomId(), response.getMessageId());
		} catch (Exception e) {
			log.error("메시지 전송 실패 : userId={}, roomId={}", userId, request.getRoomId(), e);
			sendErrorMessage(session, "메시지 전송에 실패했습니다."
				+ "");
		}
	}

	/**
	 * 특정 채팅방의 구독자들에게 메시지 브로드캐스트
	 */
	public void broadcastToRoom(Long roomId, WebSocketMessageResponse message) {
		var subscribers = webSocketSessionManager.getRoomSubscribers(roomId);

		for (Long userId : subscribers) {
			WebSocketSession userSession = webSocketSessionManager.getSession(userId);
			if (userSession != null && userSession.isOpen()) {
				sendMessage(userSession, message);
			}
		}
	}

	/**
	 * 에러 메시지 전송
	 */
	private void sendErrorMessage(WebSocketSession session, String errorMessage) {
		sendMessage(session, WebSocketMessageResponse.ofResult(errorMessage));
	}

	/**
	 * WebSocket 세션으로 메시지 전송
	 */
	private void sendMessage(WebSocketSession session, WebSocketMessageResponse message) {
		try {
			if (session.isOpen()) {
				String json = objectMapper.writeValueAsString(message);
				session.sendMessage(new TextMessage(json));
			}
		} catch (IOException e) {
			log.error("메시지 전송 실패", e);
		}
	}

}
