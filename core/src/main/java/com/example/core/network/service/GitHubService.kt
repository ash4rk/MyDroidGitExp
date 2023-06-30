package com.example.core.network.service

import com.example.core.model.User
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface  GitHubService {

    @GET("users")
    suspend fun fetchUserList(
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): ApiResponse<List<User>>

}
