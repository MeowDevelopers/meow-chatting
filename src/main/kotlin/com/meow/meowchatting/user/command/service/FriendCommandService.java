package com.meow.meowchatting.user.command.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.user.command.domain.Friend;
import com.meow.meowchatting.user.command.domain.UserProfile;
import com.meow.meowchatting.user.command.domain.error.FriendResponseCode;
import com.meow.meowchatting.user.command.dto.FriendNameUpdateRequest;
import com.meow.meowchatting.user.command.dto.FriendNameUpdateResponse;
import com.meow.meowchatting.user.command.repository.FriendCommandRepository;
import com.meow.meowchatting.user.query.service.UserProfileQueryService;

@Service
@Transactional(readOnly = false)
public class FriendCommandService {

	private final FriendCommandRepository friendCommandRepository;

	private final UserProfileQueryService userProfileQueryService;

	public FriendCommandService(FriendCommandRepository friendCommandRepository, UserProfileQueryService userProfileQueryService) {
		this.friendCommandRepository = friendCommandRepository;
		this.userProfileQueryService = userProfileQueryService;
	}

	/**
	 * 친구 이름 변경
	 */
	public DataResponse<FriendNameUpdateResponse> updateFriendName(Long userId, FriendNameUpdateRequest request) {
		Friend friend = friendCommandRepository.findById(request.getFriendId()).orElseThrow(() -> new MeowException(
			FriendResponseCode.NOT_FOUND));

		if (!Objects.equals(friend.getUserId(), userId)) throw new MeowException(FriendResponseCode.INVALID_OWNER);

		UserProfile userProfile = userProfileQueryService.findById(friend.getFriendUserId());

		friend.updateFriendName(request.getFriendName());

		return new DataResponse<>(FriendResponseCode.SUCCESS, FriendNameUpdateResponse.of(friend.getId(), userProfile.getUserProfileUrl(), friend.getFriendName()));
	}

}
