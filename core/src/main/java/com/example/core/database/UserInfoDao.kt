package com.example.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entity.UserInfoEntity

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfo: UserInfoEntity)

    @Query("SELECT * FROM userInfoTable WHERE name = :name_")
    suspend fun getUserInfo(name_: String): UserInfoEntity?

}
