package com.meow.meowchatting.chat.command.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.chat.command.domain.Message;
import com.meow.meowchatting.chat.command.domain.RoomUsers;
import com.meow.meowchatting.chat.command.domain.error.RoomUsersResponseCode;
import com.meow.meowchatting.chat.command.dto.WebSocketMessageResponse;
import com.meow.meowchatting.chat.command.enums.MessageType;
import com.meow.meowchatting.chat.command.repository.MessageCommandRepository;
import com.meow.meowchatting.chat.command.repository.RoomUsersRepository;
import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.domain.error.UserResponseCode;
import com.meow.meowchatting.user.repository.UserCommandRepository;

@Service
@Transactional(readOnly = false)
public class MessageCommandService {

	private final MessageCommandRepository messageCommandRepository;

	private final UserCommandRepository userCommandRepository;

	private final RoomUsersRepository roomUsersRepository;

	public MessageCommandService(MessageCommandRepository messageCommandRepository,
		UserCommandRepository userCommandRepository, RoomUsersRepository roomUsersRepository) {
		this.messageCommandRepository = messageCommandRepository;
		this.userCommandRepository = userCommandRepository;
		this.roomUsersRepository = roomUsersRepository;
	}

	/**
	 * 메시지 전송 및 저장
	 */
	public WebSocketMessageResponse sendMessage(Long userId, Long roomId, MessageType messageType,
		String messageText, String messageUrl) {
		validateRoomUsers(userId, roomId);

		Message message = messageCommandRepository.save(Message.of(roomId, userId, messageType, messageText, messageUrl));

		User user = userCommandRepository.findById(userId).orElseThrow(() -> new MeowException(UserResponseCode.NOT_FOUND));
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

	/**
	 * 채팅방 접근 권한 검증
	 */
	private void validateRoomUsers(Long userId, Long roomId) {
		RoomUsers roomUsers = roomUsersRepository.findByUserIdAndRoomId(userId, roomId)
			.orElseThrow(() -> new MeowException(RoomUsersResponseCode.USER_NOT_IN_CHAT_ROOM));

		if (!roomUsers.getIsActive()) {
			throw new MeowException(RoomUsersResponseCode.DISABLED_CHAT_ROOM);
		}
	}

}
