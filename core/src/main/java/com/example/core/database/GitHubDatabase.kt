package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.database.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
