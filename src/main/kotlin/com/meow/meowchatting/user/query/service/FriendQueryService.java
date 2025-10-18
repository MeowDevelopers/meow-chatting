package com.meow.meowchatting.user.query.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.common.dto.PagingRequest;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.error.FriendResponseCode;
import com.meow.meowchatting.user.query.dto.FriendListResponse;
import com.meow.meowchatting.user.query.repository.FriendPagedQueryRepository;

@Service
@Transactional(readOnly = true)
public class FriendQueryService {

	private final FriendPagedQueryRepository friendPagedQueryRepository;

	public FriendQueryService(FriendPagedQueryRepository friendPagedQueryRepository) {
		this.friendPagedQueryRepository = friendPagedQueryRepository;
	}

	/**
	 * 친구 목록 조회
	 */
	public DataResponse<Page<FriendListResponse>> friendList(Long userId, PagingRequest request) {
		Page<FriendListResponse> friendList = friendPagedQueryRepository.pagedFriendList(userId, request);
		return new DataResponse<>(FriendResponseCode.SUCCESS, friendList);
	}

}
