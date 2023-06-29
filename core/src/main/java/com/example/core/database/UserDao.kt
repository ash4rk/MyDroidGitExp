package com.example.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserList(pokemonList: List<UserEntity>)

    @Query("SELECT * FROM UserEntity WHERE page = :page_")
    suspend fun getUserList(page_: Int): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE page <= :page_")
    suspend fun getAllUserList(page_: Int): List<UserEntity>
}
