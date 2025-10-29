package com.meow.meowchatting.chat.command.dto;

import com.meow.meowchatting.chat.command.enums.MessageType;
import com.meow.meowchatting.websocket.enums.WebSocketCommandType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebSocketMessageRequest {

	private WebSocketCommandType command;

	private Long roomId;

	private MessageType messageType;

	private String messageText;

	private String messageUrl;

	public WebSocketMessageRequest(WebSocketCommandType command, Long roomId, MessageType messageType, String messageText,
		String messageUrl) {
		this.command = command;
		this.roomId = roomId;
		this.messageType = messageType;
		this.messageText = messageText;
		this.messageUrl = messageUrl;
	}

}
