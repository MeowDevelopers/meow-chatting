package com.meow.meowchatting.service.user.repository

import com.meow.meowchatting.service.user.domain.QFriend
import com.meow.meowchatting.service.user.domain.QUser
import com.meow.meowchatting.service.user.domain.QUserProfile
import com.meow.meowchatting.service.user.dto.FriendDto
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable


class UserFriendRepositoryImpl(private val em: EntityManager) : UserFriendRepositoryCustom{

    private val queryFactory: JPAQueryFactory by lazy { JPAQueryFactory(em) }

    private val user: QUser = QUser.user
    private val friend: QFriend = QFriend.friend
    private val userProfile: QUserProfile = QUserProfile.userProfile

    override fun findFriendsByUserId(userId: Long, pageable: Pageable): Page<FriendDto> {
        val content: List<FriendDto> = queryFactory
            .select(
                Projections.constructor(
                    FriendDto::class.java,
                    friend.friendUserId,
                    friend.friendName,
                    userProfile.userProfileUrl,
                    Expressions.constant("FRIEND")
                )
            )
            .from(friend)
            .join(user).on(friend.friendUserId.eq(user.id))
            .leftJoin(userProfile).on(userProfile.userId.eq(friend.friendUserId))
            .where(friend.userId.eq(userId))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = queryFactory
            .select<Long>(friend.count())
            .from(friend)
            .where(friend.userId.eq(userId))
            .fetchOne()

        return PageImpl(content, pageable, total ?: 0)
    }

}
