package com.meow.meowchatting.chat.command.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.chat.command.domain.Message;
import com.meow.meowchatting.chat.command.dto.WebSocketMessageResponse;
import com.meow.meowchatting.chat.command.enums.MessageType;
import com.meow.meowchatting.chat.command.repository.MessageCommandRepository;
import com.meow.meowchatting.chat.query.service.RoomUsersQueryService;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.query.service.UserQueryService;

@Service
@Transactional(readOnly = false)
public class MessageCommandService {

	private final MessageCommandRepository messageCommandRepository;

	private final UserQueryService userQueryService;

	private final RoomUsersQueryService roomUsersQueryService;

	public MessageCommandService(MessageCommandRepository messageCommandRepository, UserQueryService userQueryService,
		RoomUsersQueryService roomUsersQueryService) {
		this.messageCommandRepository = messageCommandRepository;
		this.userQueryService = userQueryService;
		this.roomUsersQueryService = roomUsersQueryService;
	}

	/**
	 * 메시지 전송 및 저장
	 */
	public WebSocketMessageResponse sendMessage(Long userId, Long roomId, MessageType messageType,
		String messageText, String messageUrl) {
		roomUsersQueryService.validateRoomUsers(userId, roomId);

		Message message = messageCommandRepository.save(Message.of(roomId, userId, messageType, messageText, messageUrl));

		User user = userQueryService.findById(userId);
		String senderName = user.getUserName();

		return WebSocketMessageResponse.builder()
			.messageId(message.getId())
			.roomId(message.getRoomId())
			.senderId(message.getSenderId())
			.senderName(senderName)
			.messageType(message.getMessageType())
			.messageText(message.getMessageText())
			.messageUrl(message.getMessageUrl())
			.createdAt(message.getCreatedAt())
			.build();
	}

}
