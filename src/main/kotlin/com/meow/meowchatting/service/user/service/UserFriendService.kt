package com.meow.meowchatting.service.user.service

import com.meow.meowchatting.common.response.DataResponse
import com.meow.meowchatting.service.user.domain.Friend
import com.meow.meowchatting.service.user.dto.FriendDto
import com.meow.meowchatting.service.user.dto.FriendRemovedResponseDto
import com.meow.meowchatting.service.user.dto.RenameFriendResponse
import com.meow.meowchatting.service.user.repository.UserFriendRepository
import com.meow.meowchatting.service.user.repository.UserProfileRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserFriendService(
    private val userFriendRepository: UserFriendRepository,
    private val userProfileRepository: UserProfileRepository
) {

    fun getUserFriends(userId: Long, pageable: Pageable): Page<FriendDto> {
        return userFriendRepository.findFriendsByUserId(userId, pageable)
    }

    @Transactional
    fun modFriendName(userId: Long, friendId: Long, userName: String): DataResponse<RenameFriendResponse> {
        try {
            val friend: Friend = userFriendRepository.findByUserIdAndFriendUserId(userId, friendId)
                ?: throw IllegalArgumentException()

            friend.friendName = userName.trim();

            userFriendRepository.save(friend)

            val userProfile = userProfileRepository.findByUserId(friendId)
                ?: throw NoSuchElementException()

            return DataResponse(
                status = 200,
                message = "정상 처리 완료",
                response = RenameFriendResponse(
                    userId = friend.friendUserId,
                    userProfile = userProfile.userProfileUrl,
                    userName = friend.friendName
                )
            )
        } catch (e: IllegalArgumentException) {
            return DataResponse(
                status = 400,
                message = e.message ?: "친구 관계를 찾을 수 없습니다. userId=$userId, friendId=$friendId\""
            )
        } catch (e: NoSuchElementException) {
            return DataResponse(
                status = 404,
                message = e.message ?: "프로필 정보가 존재하지 않습니다. userId=$friendId\""
            )
        }
    }

    @Transactional
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
