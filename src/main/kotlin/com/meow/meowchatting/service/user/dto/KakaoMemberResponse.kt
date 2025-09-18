package com.meow.meowchatting.service.user.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.meow.meowchatting.service.user.domain.User
import com.meow.meowchatting.service.user.enums.ProviderType
import com.meow.meowchatting.service.user.enums.UserType
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KakaoMemberResponse(
    val id: Long,
    val hasSignedUp: Boolean,
    val connectedAt: LocalDateTime,
    val kakaoAccount: KakaoAccount
)
{
    fun toDomain(): User {
        val currentDateTime = LocalDateTime.now()
        return User().apply {
            providerType = ProviderType.KAKAO
            userLastLoginDate = currentDateTime.toString()
            providerKey = kakaoAccount.email
            userEmail = kakaoAccount.email
            userNickName = kakaoAccount.profile.nickname
            userProfile = kakaoAccount.profile.profileImageUrl
            dormancy = false
            userType = UserType.BASIC_USER
        }
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class KakaoAccount(
        val profileNeedsAgreement: Boolean,
        val profileNicknameNeedsAgreement: Boolean,
        val profileImageNeedsAgreement: Boolean,
        val profile: Profile,
        val nameNeedsAgreement: Boolean,
        val name: String,
        val emailNeedsAgreement: Boolean,
        val isEmailValid: Boolean,
        val isEmailVerified: Boolean,
        val email: String,
        val ageRangeNeedsAgreement: Boolean,
        val ageRange: String,
        val birthyearNeedsAgreement: Boolean,
        val birthyear: String,
        val birthdayNeedsAgreement: Boolean,
        val birthday: String,
        val birthdayType: String,
        val genderNeedsAgreement: Boolean,
        val gender: String,
        val phoneNumberNeedsAgreement: Boolean,
        val phoneNumber: String,
        val ciNeedsAgreement: Boolean,
        val ci: String,
        val ciAuthenticatedAt: LocalDateTime
    )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Profile(
        val nickname: String,
        val thumbnailImageUrl: String,
        val profileImageUrl: String,
        val isDefaultImage: Boolean
    )
}
