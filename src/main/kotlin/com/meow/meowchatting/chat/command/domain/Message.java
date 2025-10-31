package com.meow.meowchatting.chat.command.domain;

import org.hibernate.annotations.SQLRestriction;

import com.meow.meowchatting.chat.command.enums.MessageType;
import com.meow.meowchatting.common.base.AbstractBaseUserByEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message",
	indexes = {
		@Index(name = "idx_room_created_at", columnList = "room_id, created_at")
	})
@SQLRestriction("deleted_at IS NULL")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "message_id"))
public class Message extends AbstractBaseUserByEntity {

	@Column(name = "room_id", nullable = false)
	private Long roomId;

	@Column(name = "sender_id", nullable = false)
	private Long senderId;

	@Enumerated(EnumType.STRING)
	@Column(name = "message_type", nullable = false, length = 10)
	private MessageType messageType;

	@Column(name = "message_text", columnDefinition = "TEXT")
	private String messageText;

	@Column(name = "message_url", length = 500)
	private String messageUrl;

	@Builder
	public Message(Long roomId, Long senderId, MessageType messageType, String messageText, String messageUrl) {
		this.roomId = roomId;
		this.senderId = senderId;
		this.messageType = messageType;
		this.messageText = messageText;
		this.messageUrl = messageUrl;
	}

	public static Message of(Long roomId, Long senderId, MessageType messageType, String messageText, String messageUrl) {
		return Message.builder()
			.roomId(roomId)
			.senderId(senderId)
			.messageType(messageType)
			.messageText(messageText)
			.messageUrl(messageUrl)
			.build();
	}
}
