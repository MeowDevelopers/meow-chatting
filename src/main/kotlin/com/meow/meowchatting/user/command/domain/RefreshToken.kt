package com.meow.meowchatting.user.command.domain

import com.meow.meowchatting.common.base.AbstractBaseUserByEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "refresh_token")
@AttributeOverride(name ="id", column = Column(name = "refresh_token_id"))
class RefreshToken (

    @Column(name = "user_id", nullable = false)
    val userId : Long,

    @Column(name = "refresh_token", nullable = true)
    var refreshToken: String

) : AbstractBaseUserByEntity(){
    fun update(newToken: String): RefreshToken {
        this.refreshToken = newToken
        this.modifiedAt = LocalDateTime.now()
        return this
    }
}
