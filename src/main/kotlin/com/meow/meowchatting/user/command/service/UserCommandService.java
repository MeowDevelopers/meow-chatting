package com.meow.meowchatting.user.command.service;

import com.meow.meowchatting.common.response.DataResponse;
import com.meow.meowchatting.s3.enums.S3Bucket;
import com.meow.meowchatting.s3.service.S3Service;
import com.meow.meowchatting.user.command.domain.UserProfile;
import com.meow.meowchatting.user.query.dto.UserResponseCode;
import com.meow.meowchatting.user.query.exception.UserException;
import com.meow.meowchatting.user.repository.RefreshTokenRepository;
import com.meow.meowchatting.user.repository.UserCommandRepository;
import com.meow.meowchatting.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = false)
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserProfileRepository userProfileRepository;
    private final S3Service s3Service;

    public UserCommandService(UserCommandRepository userCommandRepository, RefreshTokenRepository refreshTokenRepository, UserProfileRepository userProfileRepository, S3Service s3Service) {
        this.userCommandRepository = userCommandRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userProfileRepository = userProfileRepository;
        this.s3Service = s3Service;
    }

    public DataResponse<String> updateUserProfile(Long userId, MultipartFile profileImage){
        UserProfile user  = userProfileRepository.findByUserId(userId)
                .orElseThrow(()-> new UserException(UserResponseCode.NOT_FOUND_USER));

        String key = s3Service.uploadFile(profileImage, S3Bucket.UserProfileBucket);
        user.updateProfileUrl(key);
        userProfileRepository.save(user);

        return new DataResponse<>(UserResponseCode.SUCCESS, key);
    }

}
