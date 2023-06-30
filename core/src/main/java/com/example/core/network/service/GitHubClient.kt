package com.example.core.network.service

import com.example.core.model.User
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class GitHubClient @Inject constructor(
    private val githubService: GitHubService
) {

    suspend fun fetchUserList(
        page: Int
    ): ApiResponse<List<User>> =
        githubService.fetchUserList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )

    companion object {
        private const val PAGING_SIZE = 30
    }
}
