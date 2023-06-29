package com.example.core.database.entity.mapper

import com.example.core.database.entity.UserEntity
import com.example.core.model.User

object UserEntityMapper : EntityMapper<List<User>, List<UserEntity>> {

    override fun asEntity(domain: List<User>): List<UserEntity> {
        return domain.map { user ->
            UserEntity(
                page = user.page,
                name = user.name,
                url = user.url,
                avatar_url = user.avatarUrl
            )
        }
    }

    override fun asDomain(entity: List<UserEntity>): List<User> {
        return entity.map { userEntity ->
            User(
                page = userEntity.page,
                name = userEntity.name,
                url = userEntity.url,
                avatarUrl = userEntity.avatar_url
            )
        }
    }
}

fun List<User>.asEntity(): List<UserEntity> {
    return UserEntityMapper.asEntity(this)
}

fun List<UserEntity>.asDomain(): List<User> {
    return UserEntityMapper.asDomain(this)
}
