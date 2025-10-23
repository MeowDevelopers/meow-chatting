package com.meow.meowchatting.chat.command.service;

import com.meow.meowchatting.chat.command.domain.Room;
import com.meow.meowchatting.chat.command.domain.error.RoomResponseCode;
import com.meow.meowchatting.chat.command.repository.RoomCommandRepository;
import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomDeleteService {

    private final RoomCommandRepository roomCommandRepository;

    /**
     * 채팅방 삭제
     */
    public DataResponse<Void> deleteChatRoom(Long userId, Long roomId) {
        Room room = roomCommandRepository.findById(roomId)
                .orElseThrow(() -> new MeowException(RoomResponseCode.NOT_FOUND));

        if (!Objects.equals(room.getRoomOwnerUserId(), userId)) {
            throw new MeowException(RoomResponseCode.INVALID_OWNER);
        }

        room.deleteRoom();
        return new DataResponse<>(RoomResponseCode.SUCCESS);
    }

}
