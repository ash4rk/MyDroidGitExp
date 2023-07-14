package com.ash4rk.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    var page: Int = 0,
    @PrimaryKey val name: String,
    val url: String,
    val avatar_url: String
)
