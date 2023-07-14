package com.ash4rk.core.database.entity.mapper

import com.ash4rk.core.database.entity.UserInfoEntity
import com.ash4rk.core.model.UserInfo

object UserInfoEntityMapper : EntityMapper<UserInfo, UserInfoEntity> {

    override fun asEntity(domain: UserInfo): UserInfoEntity {
        return UserInfoEntity(
            id = domain.id,
            name = domain.name,
            login = domain.login,
            url = domain.url,
            avatar_url = domain.avatarUrl,
            company = domain.company ?: "not specified",
            blog = domain.blog,
            location = domain.location ?: "not specified",
            public_repos = domain.public_repos,
            public_gists = domain.public_gists,
            followers = domain.followers,
            following = domain.following,
            created_at = domain.created_at
        )
    }

    override fun asDomain(entity: UserInfoEntity): UserInfo {
        return UserInfo(
            id = entity.id,
            name = entity.name,
            login = entity.login,
            url = entity.url,
            avatarUrl = entity.avatar_url,
            company = entity.company,
            blog = entity.blog,
            location = entity.location,
            public_repos = entity.public_repos,
            public_gists = entity.public_gists,
            followers = entity.followers,
            following = entity.following,
            created_at =  entity.created_at
        )
    }

}

fun UserInfo.asEntity(): UserInfoEntity {
    return UserInfoEntityMapper.asEntity(this)
}

fun UserInfoEntity.asDomain(): UserInfo {
    return UserInfoEntityMapper.asDomain(this)
}
