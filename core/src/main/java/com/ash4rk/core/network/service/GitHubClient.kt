package com.ash4rk.core.network.service

import com.ash4rk.core.model.User
import com.ash4rk.core.model.UserInfo
import com.ash4rk.core.network.model.SearchUserResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class GitHubClient @Inject constructor(
    private val githubService: GitHubService
) {

    suspend fun fetchUserList(
        page: Int
    ): ApiResponse<List<User>> =
        githubService.fetchUserList(
            per_page = PAGING_SIZE,
            since = page * PAGING_SIZE
        )

    suspend fun fetchSearchUserList(
        page: Int,
        query: String
    ): ApiResponse<SearchUserResponse> =
        githubService.fetchSearchUserList(
            query = query,
            per_page = PAGING_SIZE,
            page = page
        )

    suspend fun fetchUserInfo(
        name: String
    ): ApiResponse<UserInfo> =
        githubService.fetchUserInfo(
            name = name
        )

    companion object {
        private const val PAGING_SIZE = 30
    }
}
