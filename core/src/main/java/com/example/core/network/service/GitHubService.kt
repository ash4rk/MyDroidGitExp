package com.example.core.network.service

import com.example.core.network.model.UserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface  GitHubService {

    @GET("user")
    suspend fun fetchUserList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<UserResponse>

}
