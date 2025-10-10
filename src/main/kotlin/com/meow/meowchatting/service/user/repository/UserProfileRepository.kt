package com.meow.meowchatting.service.user.repository

import com.meow.meowchatting.service.user.domain.UserProfile
import org.springframework.data.jpa.repository.JpaRepository

interface UserProfileRepository: JpaRepository<UserProfile, Long> {
    fun findByUserId(userId: Long): UserProfile?
}
