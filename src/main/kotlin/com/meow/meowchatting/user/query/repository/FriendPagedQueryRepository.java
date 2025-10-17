package com.meow.meowchatting.user.query.repository;

import org.springframework.data.domain.Page;

import com.meow.meowchatting.common.dto.PagingRequest;
import com.meow.meowchatting.user.query.dto.FriendListResponse;

public interface FriendPagedQueryRepository {

	Page<FriendListResponse> pagedFriendList(Long userId, PagingRequest request);

}
