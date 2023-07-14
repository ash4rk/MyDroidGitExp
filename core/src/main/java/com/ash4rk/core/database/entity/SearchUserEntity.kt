package com.ash4rk.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchUserTable")
data class SearchUserEntity(
    var page: Int = 0,
    @PrimaryKey val name: String,
    val url: String,
    val avatar_url: String
)
