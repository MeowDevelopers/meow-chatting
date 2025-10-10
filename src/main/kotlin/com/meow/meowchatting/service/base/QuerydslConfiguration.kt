package com.meow.meowchatting.service.base

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class QuerydslConfiguration {

    @Bean
    fun jpaQueryFactory(entityManager: EntityManager?): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }

}
