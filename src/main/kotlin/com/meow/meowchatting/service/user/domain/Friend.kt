package com.meow.meowchatting.service.user.domain

import com.meow.meowchatting.service.base.AbstractBaseEntity
import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

@Setter
@Getter
@Entity
@Table(name = "friend")
@AttributeOverride(name = "id", column = Column(name = "friend_id"))
class Friend : AbstractBaseEntity() {

    @Column(name = "user_id", nullable = false)
    var userId: Long = 0

    @Column(name = "friend_user_id", nullable = false)
    var friendUserId: Long = 0

    @Column(name = "friend_name", nullable = false)
    var friendName: String = ""

    @Column(name = "is_blocked", nullable = false)
    var isBlocked: Boolean = false

    @Column(name = "deleted_at", nullable = false)
    var deletedAt: LocalDateTime = LocalDateTime.now()

}
