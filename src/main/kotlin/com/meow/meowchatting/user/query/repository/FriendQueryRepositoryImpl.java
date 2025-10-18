package com.meow.meowchatting.user.query.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.meow.meowchatting.common.dto.PagingRequest;
import com.meow.meowchatting.user.command.domain.QFriend;
import com.meow.meowchatting.user.command.domain.QUserProfile;
import com.meow.meowchatting.user.query.dto.FriendListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class FriendQueryRepositoryImpl implements FriendPagedQueryRepository {

	private final JPAQueryFactory queryFactory;

	private final QFriend friend = QFriend.friend;

	private final QUserProfile userProfile = QUserProfile.userProfile;

	public FriendQueryRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	/**
	 * 친구 목록 조회
	 */
	@Override
	public Page<FriendListResponse> pagedFriendList(Long userId, PagingRequest request) {
		Pageable paging = PageRequest.of(request.getPage() - 1, request.getSize());

		List<FriendListResponse> result = queryFactory
			.select(Projections.constructor(FriendListResponse.class,
				friend.id, friend.friendName, userProfile.userProfileUrl, friend.friendStatus))
			.from(friend)
			.leftJoin(userProfile).on(friend.friendUserId.eq(userProfile.userId))
			.where(friend.userId.eq(userId))
			.orderBy(friend.friendName.asc())
			.offset(paging.getOffset())
			.limit(paging.getPageSize())
			.fetch();

		Long total = Optional.ofNullable(queryFactory
			.select(friend.countDistinct())
			.from(friend)
			.where(friend.userId.eq(userId))
			.fetchOne()).orElse(0L);

		return new PageImpl<>(result, paging, total);
	}
}
