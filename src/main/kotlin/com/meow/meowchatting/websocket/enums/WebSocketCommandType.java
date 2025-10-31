package com.meow.meowchatting.websocket.enums;

import lombok.Getter;

@Getter
public enum WebSocketCommandType {

	SUBSCRIBE("채팅방 구독(입장)"),

	UNSUBSCRIBE("채팅방 구독 해제"),

	SEND("메시지 전송");

	private final String description;

	WebSocketCommandType(String description) {
		this.description = description;
	}

}
