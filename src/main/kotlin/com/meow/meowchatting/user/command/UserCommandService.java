package com.meow.meowchatting.user.command;

import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.repository.RefreshTokenRepository;
import com.meow.meowchatting.user.repository.UserCommandRepository;
import com.meow.meowchatting.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class UserCommandService {

    private final UserCommandRepository userCommandRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserProfileRepository userProfileRepository;

    public UserCommandService(UserCommandRepository userCommandRepository, RefreshTokenRepository refreshTokenRepository, UserProfileRepository userProfileRepository) {
        this.userCommandRepository = userCommandRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userProfileRepository = userProfileRepository;
    }

}
