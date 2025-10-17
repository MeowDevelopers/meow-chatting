package com.meow.meowchatting.user.query.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.user.command.domain.UserProfile;
import com.meow.meowchatting.user.command.domain.error.UserProfileResponseCode;
import com.meow.meowchatting.user.query.repository.UserProfileQueryRepository;

@Service
@Transactional(readOnly = true)
public class UserProfileQueryService {

	private final UserProfileQueryRepository userProfileQueryRepository;

	public UserProfileQueryService(UserProfileQueryRepository userProfileQueryRepository) {
		this.userProfileQueryRepository = userProfileQueryRepository;
	}

	public UserProfile findById(Long userProfileId) {
		return userProfileQueryRepository.findById(userProfileId).orElseThrow(() -> new MeowException(
			UserProfileResponseCode.NOT_FOUND));
	}

}
