package com.meow.meowchatting.service.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private val modifiedAt: LocalDateTime? = null

}
