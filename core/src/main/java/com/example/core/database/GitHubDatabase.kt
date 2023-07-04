package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.entity.SearchUserEntity
import com.example.core.database.entity.UserEntity
import com.example.core.database.entity.UserInfoEntity

@Database(
    entities = [UserEntity::class,
                SearchUserEntity::class,
                UserInfoEntity::class],
    version = 7,
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun searchUserDao(): SearchUserDao

    abstract fun userInfoDao(): UserInfoDao
}
