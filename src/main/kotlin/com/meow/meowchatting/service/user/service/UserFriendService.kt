package com.meow.meowchatting.service.user.service

import com.meow.meowchatting.service.user.domain.Friend
import com.meow.meowchatting.service.user.dto.FriendDto
import com.meow.meowchatting.service.user.dto.FriendRemovedResponseDto
import com.meow.meowchatting.service.user.repository.UserFriendRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserFriendService(private val userFriendRepository: UserFriendRepository) {

    fun getUserFriends(userId: Long, pageable: Pageable): Page<FriendDto> {
        return userFriendRepository.findFriendsByUserId(userId, pageable)
    }

    fun modFriendName() {}

    fun deleteUserFriend(userId: Long, friendId: Long, isHardDelete: Boolean): FriendRemovedResponseDto {
        val friend: Friend = userFriendRepository.findByUserIdAndFriendUserId(userId, friendId)
            ?: throw IllegalArgumentException("친구 관계를 찾을 수 없습니다. userId=$userId, friendId=$friendId")

        if (isHardDelete) {
            userFriendRepository.delete(friend);
        } else {
            friend.deletedAt = LocalDateTime.now()
            userFriendRepository.save(friend)
        }

        return FriendRemovedResponseDto(
            userId = friend.friendUserId,
            friendName = friend.friendName,
            friendStatus = "REMOVED",
            friendStatusMessage = "친구가 삭제되었습니다."
        )
    }
}
