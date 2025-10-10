package com.meow.meowchatting.service.user.repository

import com.meow.meowchatting.service.user.domain.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFriendRepository : JpaRepository<Friend, Long>, UserFriendRepositoryCustom {
    fun findByUserIdAndFriendUserId(userId: Long, friendId: Long): Friend?
}
