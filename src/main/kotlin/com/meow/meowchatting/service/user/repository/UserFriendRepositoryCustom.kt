package com.meow.meowchatting.service.user.repository

import com.meow.meowchatting.service.user.dto.FriendDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserFriendRepositoryCustom {
    fun findFriendsByUserId(userId: Long, pageable: Pageable): Page<FriendDto>
}
