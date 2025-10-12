package com.meow.meowchatting.user.command.domain

import com.meow.meowchatting.common.base.AbstractBaseUserByEntity
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_profile")
@AttributeOverride(name = "id", column = Column(name="user_profile_id"))
class UserProfile (

    @Column(name = "user_id", nullable = false)
    val userId : Long,

    @Column(name = "user_profile_url", nullable = false)
    val userProfileUrl : String

): AbstractBaseUserByEntity()
