package com.example.core.database.entity.mapper

import com.example.core.database.entity.SearchUserEntity
import com.example.core.database.entity.UserEntity
import com.example.core.model.User

object SearchUserEntityMapper : EntityMapper<List<User>, List<SearchUserEntity>> {

    override fun asEntity(domain: List<User>): List<SearchUserEntity> {
        return domain.map { user ->
            SearchUserEntity(
                page = user.page,
                name = user.name,
                url = user.url,
                avatar_url = user.avatarUrl
            )
        }
    }

    override fun asDomain(entity: List<SearchUserEntity>): List<User> {
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

fun List<User>.asSearchEntity(): List<SearchUserEntity> {
    return SearchUserEntityMapper.asEntity(this)
}

fun List<SearchUserEntity>.asDomain(): List<User> {
    return SearchUserEntityMapper.asDomain(this)
}
