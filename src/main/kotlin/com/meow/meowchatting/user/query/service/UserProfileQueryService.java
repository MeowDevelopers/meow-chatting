package com.meow.meowchatting.user.query.service;

import com.meow.meowchatting.s3.enums.S3Bucket;
import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.s3.service.S3Service;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.query.dto.UserQueryProfileResponse;
import com.meow.meowchatting.user.query.dto.UserResponseCode;
import com.meow.meowchatting.user.query.exception.UserException;
import com.meow.meowchatting.user.query.repository.UserQueryRepository;
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
	private final UserQueryRepository userQueryRepository;
	private final S3Service s3Service;

	public UserProfileQueryService(UserProfileQueryRepository userProfileQueryRepository,
								   UserQueryRepository userQueryRepository, S3Service s3Service) {
		this.userProfileQueryRepository = userProfileQueryRepository;
		this.userQueryRepository = userQueryRepository;
		this.s3Service = s3Service;
	}

	public UserProfile findById(Long userProfileId) {
		return userProfileQueryRepository.findById(userProfileId).orElseThrow(() -> new MeowException(
			UserProfileResponseCode.NOT_FOUND));
	}

	public DataResponse<UserQueryProfileResponse> getUserProfile(Long userId){
		UserProfile userProfile = userProfileQueryRepository.findById(userId)
				.orElseThrow(()-> new UserException(UserResponseCode.NOT_FOUND_USER));

		User user = userQueryRepository.findById(userProfile.getUserId())
				.orElseThrow(()-> new UserException(UserResponseCode.NOT_FOUND_USER));

		byte[] profileImage = s3Service.downloadFile(S3Bucket.UserProfileBucket, userProfile.getUserProfileUrl());

		return new DataResponse<>(UserProfileResponseCode.SUCCESS,
				UserQueryProfileResponse.of(user.getUserName(), profileImage));
	}
}
