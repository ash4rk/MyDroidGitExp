package com.ash4rk.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfoTable")
data class UserInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val login: String,
    val url: String,
    val avatar_url: String,
    val company: String,
    val blog: String,
    val location: String,
    val public_repos: Int,
    val public_gists: Int,
    val followers: Int,
    val following: Int,
    val created_at: String,
)
