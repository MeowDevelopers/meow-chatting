package com.meow.meowchatting.user.query.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.domain.error.UserResponseCode;
import com.meow.meowchatting.user.query.repository.UserQueryRepository;

@Service
@Transactional(readOnly = true)
public class UserQueryService {

	private final UserQueryRepository userQueryRepository;

	public UserQueryService(UserQueryRepository userQueryRepository) {
		this.userQueryRepository = userQueryRepository;
	}

	public User findById(Long userId) {
		return userQueryRepository.findById(userId).orElseThrow(() -> new MeowException(UserResponseCode.NOT_FOUND));
	}
}
