package com.meow.meowchatting.service.user.domain

import com.meow.meowchatting.service.user.enums.ProviderType
import com.meow.meowchatting.service.user.enums.UserType
import jakarta.persistence.*
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@Entity
@NoArgsConstructor
open class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    open lateinit var providerType: ProviderType

    @Column(name = "user_last_login_date", nullable = false)
    open lateinit var userLastLoginDate: String

    @Column(name = "provider_key", nullable = true)
    open lateinit var providerKey: String

    @Column(name = "user_email", nullable = true)
    open lateinit var userEmail: String

    @Column(name = "user_nickname", nullable = false)
    open lateinit var userNickName: String

    @Column(name = "user_profile", nullable = false)
    open lateinit var userProfile: String

    @Column(name = "dormancy", nullable = false, columnDefinition = "boolean default false")
    open var dormancy: Boolean = false

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    open lateinit var userType: UserType

    @Builder
    constructor(
        providerType: ProviderType,
        userLastLoginDate: String,
        providerKey: String,
        userEmail: String,
        userNickName: String,
        userProfile: String,
        dormancy: Boolean,
        userType: UserType
    ) : this() {
        this.userProfile = userProfile
        this.userEmail = userEmail
        this.userType = userType
        this.userNickName = userNickName
        this.providerType = providerType
        this.providerKey = providerKey
        this.userLastLoginDate = userLastLoginDate
        this.dormancy = dormancy
    }
}
