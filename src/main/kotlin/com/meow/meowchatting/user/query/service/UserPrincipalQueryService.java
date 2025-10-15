package com.meow.meowchatting.user.query.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meow.meowchatting.common.exception.MeowException;
import com.meow.meowchatting.user.command.domain.User;
import com.meow.meowchatting.user.command.domain.UserPrincipal;
import com.meow.meowchatting.user.domain.error.UserResponseCode;
import com.meow.meowchatting.user.query.repository.UserQueryRepository;

import io.jsonwebtoken.Claims;

@Service
@Transactional(readOnly = true)
public class UserPrincipalQueryService {

	private final UserQueryRepository userQueryRepository;

	public UserPrincipalQueryService(UserQueryRepository userQueryRepository) {
		this.userQueryRepository = userQueryRepository;
	}

	public UserDetails loadUserByUserId(Long userId, Claims claims) {
		User user = userQueryRepository.findById(userId).orElseThrow(() -> new MeowException(UserResponseCode.NOT_FOUND));
		return new UserPrincipal(user, claims);
	}
}
