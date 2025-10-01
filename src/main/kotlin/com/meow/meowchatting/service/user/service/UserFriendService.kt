package com.meow.meowchatting.service.user.service

import com.meow.meowchatting.service.user.dto.FriendDto
import com.meow.meowchatting.service.user.repository.UserFriendRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserFriendService(private val userFriendRepository: UserFriendRepository) {

    fun getUserFriends(userId: Long, pageable: Pageable): Page<FriendDto> {
        return userFriendRepository.findFriendsByUserId(userId, pageable)
    }

    fun modFriendName() {}

    fun userFriendsDelete() {}
}
