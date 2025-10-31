package com.meow.meowchatting.chat.command.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meow.meowchatting.chat.command.enums.MessageType;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebSocketMessageResponse {

	private Long messageId;

	private Long roomId;

	private Long senderId;

	private String senderName;

	private MessageType messageType;

	private String messageText;

	private String messageUrl;

	private LocalDateTime createdAt;

	private String resultMessage;

	@Builder
	public WebSocketMessageResponse(Long messageId, Long roomId, Long senderId, String senderName, MessageType messageType,
		String messageText, String messageUrl, LocalDateTime createdAt, String resultMessage) {
		this.messageId = messageId;
		this.roomId = roomId;
		this.senderId = senderId;
		this.senderName = senderName;
		this.messageType = messageType;
		this.messageText = messageText;
		this.messageUrl = messageUrl;
		this.createdAt = createdAt;
		this.resultMessage = resultMessage;
	}

	public static WebSocketMessageResponse ofRoomAction(Long roomId, String resultMessage) {
		return WebSocketMessageResponse.builder()
			.roomId(roomId)
			.resultMessage(resultMessage)
			.build();
	}

	public static WebSocketMessageResponse ofResult(String resultMessage) {
		return WebSocketMessageResponse.builder()
			.resultMessage(resultMessage)
			.build();
	}

}
