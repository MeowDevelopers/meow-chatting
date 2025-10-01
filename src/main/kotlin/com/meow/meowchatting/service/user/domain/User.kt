package com.meow.meowchatting.service.user.domain

import com.meow.meowchatting.service.base.AbstractBaseEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity

import jakarta.persistence.Table
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

@Setter
@Getter
@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = Column(name = "user_id"))
class User : AbstractBaseEntity(){

    @Column(name = "user_email", nullable = false)
    val userEmail: String = ""

    @Column(name = "user_name", nullable = false)
    val userName: String = ""

    @Column(name = "provider_type", nullable = true)
    val providerType: String = ""

    @Column(nullable = true)
    val dormancy : String = ""

    @Column(name = "deleted_at", nullable = false)
    var deletedAt: LocalDateTime = LocalDateTime.now()
}
