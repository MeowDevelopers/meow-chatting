package com.meow.meowchatting.chat.query.repository;

import com.meow.meowchatting.chat.command.domain.QRoom;
import com.meow.meowchatting.chat.command.domain.QRoomUsers;
import com.meow.meowchatting.chat.query.dto.ChatRoomListResponse;
import com.meow.meowchatting.common.dto.PagingRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomQueryRepositoryImpl implements RoomPagedQueryRepository {

    private final JPAQueryFactory queryFactory;

    private final QRoom room = QRoom.room;
    private final QRoomUsers roomUsers = QRoomUsers.roomUsers;

    public RoomQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    /**
     * 채팅방 목록 조회
     */
    @Override
    public Page<ChatRoomListResponse> pagedChatRoomList(Long userId, PagingRequest request) {
        Pageable paging = PageRequest.of(request.getPage() - 1, request.getSize());

        List<ChatRoomListResponse> result = queryFactory
                .select(Projections.constructor(ChatRoomListResponse.class,
                        room.roomId, room.roomType, room.roomName, room.roomOwnerUserId, room.createdAt, room.modifiedAt))
                .from(roomUsers)
                .innerJoin(room).on(roomUsers.roomId.eq(room.roomId))
                .where(roomUsers.userId.eq(userId))
                .orderBy(room.modifiedAt.desc())
                .offset(paging.getOffset())
                .limit(paging.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(queryFactory
                .select(roomUsers.countDistinct())
                .from(roomUsers)
                .innerJoin(room).on(roomUsers.roomId.eq(room.roomId))
                .where(roomUsers.userId.eq(userId))
                .fetchOne()).orElse(0L);

        return new PageImpl<>(result, paging, total);
    }
}
