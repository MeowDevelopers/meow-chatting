package com.meow.meowchatting.common.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AbstractBaseUserByEntity : AbstractBaseEntity(){

    @Column(name = "deleted_at", nullable = true)
    open var deletedAt : LocalDateTime?= null
    open fun markDeleted() {
        this.deletedAt = LocalDateTime.now()
    }
}
