package com.meow.meowchatting.websocket.manager;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket 세션 관리
 * 유저별 세션과 채팅방별 구독 정보를 메모리에 저장
 */
@Slf4j
@Component
public class WebSocketSessionManager {

	private final Map<Long, WebSocketSession> userSessions = new ConcurrentHashMap<>(); // 유저 세션

	private final Map<Long, Set<Long>> userSubscriptions = new ConcurrentHashMap<>(); // 유저가 구독 중인 채팅방 목록

	private final Map<Long, Set<Long>> roomSubscribers = new ConcurrentHashMap<>(); // 채팅방을 구독 중인 유저 목록

	/**
	 * 세션 추가
	 */
	public void addSession(Long userId, WebSocketSession session) {
		userSessions.put(userId, session);
		log.debug("세션 추가 : userId={}", userId);
	}

	/**
	 * 세션 제거
	 */
	public void removeSession(Long userId) {
		if (userSessions.remove(userId) != null) {
			log.debug("세션 제거 : userId={}", userId);
		}

		// 구독 정보 제거
		Set<Long> subscribedRooms = userSubscriptions.remove(userId);
		if (subscribedRooms == null || subscribedRooms.isEmpty()) {
			return;
		}

		// 각 방에서 해당 유저 제거 + 비어 있으면 방 자체 삭제
		subscribedRooms.forEach(roomId -> roomSubscribers.computeIfPresent(roomId, (id, subscribers) -> {
				subscribers.remove(userId);
				return subscribers.isEmpty() ? null : subscribers;
			})
		);
	}

	/**
	 * 유저 세션 조회
	 */
	public WebSocketSession getSession(Long userId) {
		return userSessions.get(userId);
	}

	/**
	 * 채팅방 구독
	 */
	public void subscribeRoom(Long userId, Long roomId) {
		userSubscriptions.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(roomId);
		roomSubscribers.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(userId);
		log.debug("채팅방 구독 : userId={}, roomId={}", userId, roomId);
	}

	/**
	 * 채팅방 구독 해제
	 */
	public void unsubscribeRoom(Long userId, Long roomId) {
		Set<Long> userRooms = userSubscriptions.get(userId);
		if (userRooms != null) {
			userRooms.remove(roomId);
			if (userRooms.isEmpty()) {
				userSubscriptions.remove(userId);
			}
		}

		Set<Long> roomUsers = roomSubscribers.get(roomId);
		if (roomUsers != null) {
			roomUsers.remove(userId);
			if (roomUsers.isEmpty()) {
				roomSubscribers.remove(roomId);
			}
		}
		log.debug("채팅방 구독 해제 : userId={}, roomId={}", userId, roomId);
	}

	/**
	 * 특정 채팅방을 구독 중인 유저 목록 조회
	 */
	public Set<Long> getRoomSubscribers(Long roomId) {
		return roomSubscribers.getOrDefault(roomId, Collections.emptySet());
	}

	/**
	 * 유저가 특정 채팅방을 구독 중인지 확인
	 */
	public boolean isSubscribed(Long userId, Long roomId) {
		Set<Long> userRooms = userSubscriptions.get(userId);
		return userRooms != null && userRooms.contains(roomId);
	}

}
