package com.meow.meowchatting.user.command.domain

import com.meow.meowchatting.common.base.AbstractBaseUserByEntity
import com.meow.meowchatting.user.command.enums.ProviderType
import jakarta.persistence.*

@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = Column(name="user_id"))
class User (

    @Column(name= "user_email", nullable = false)
    val userEmail : String,

    @Column(name = "user_name", nullable = false)
    val userName : String,

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    val providerType : ProviderType,

    @Column(name = "provider_id", nullable = true)
    val providerId : String,

    @Column(name = "dormancy", nullable = false)
    val dormancy : Boolean = false,

) : AbstractBaseUserByEntity()
