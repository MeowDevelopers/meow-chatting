package com.meow.meowchatting.service.user.domain

import com.meow.meowchatting.service.base.AbstractBaseEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_profile")
@AttributeOverride(name = "id", column = Column(name = "user_profile_id"))
class UserProfile : AbstractBaseEntity() {

    @Column(name = "user_id", nullable = false)
    var userId: Long = 0

    @Column(name = "user_profile_url", nullable = false)
    var userProfileUrl: String = ""

    @Column(name = "deleted_at", nullable = false)
    var deletedAt: LocalDateTime = LocalDateTime.now()

}
