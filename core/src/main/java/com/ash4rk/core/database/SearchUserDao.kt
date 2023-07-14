package com.ash4rk.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ash4rk.core.database.entity.SearchUserEntity

@Dao
interface SearchUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchUserList(searchUserList: List<SearchUserEntity>)

    @Query("SELECT * FROM searchUserTable WHERE name LIKE '%' || :query_ || '%' AND page = :page_")
    suspend fun getSearchUserList(query_: String, page_: Int): List<SearchUserEntity>

    @Query("SELECT * FROM searchUserTable WHERE page <= :page_")
    suspend fun getAllSearchUserList(page_: Int): List<SearchUserEntity>

    @Query("DELETE FROM searchUserTable")
    suspend fun clearSearchUserTable()

    companion object {
        var last_query: String = ""
    }
}
