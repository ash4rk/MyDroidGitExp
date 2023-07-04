package com.example.core.network.service

import com.example.core.model.User
import com.example.core.model.UserInfo
import com.example.core.network.model.SearchUserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  GitHubService {

    @GET("users")
    suspend fun fetchUserList(
        @Query("since") since: Int = 0,
        @Query("per_page") per_page: Int = 30
    ): ApiResponse<List<User>>

    @GET("/search/users")
    suspend fun fetchSearchUserList(
        @Query("q") query: String = "",
        @Query("sort") sort: String = "",
        @Query("order") order: String = "desc",
        @Query("per_page") per_page: Int = 30,
        @Query("page") page: Int = 1
    ): ApiResponse<SearchUserResponse>

    @GET("users/{name}")
    suspend fun fetchUserInfo(@Path("name") name: String): ApiResponse<UserInfo>

}
