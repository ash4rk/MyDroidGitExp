package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.entity.SearchUserEntity
import com.example.core.database.entity.UserEntity

@Database(
    entities = [UserEntity::class,
                SearchUserEntity::class],
    version = 3,
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun searchUserDao(): SearchUserDao
}
