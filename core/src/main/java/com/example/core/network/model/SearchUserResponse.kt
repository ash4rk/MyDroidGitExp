package com.example.core.network.model

import com.example.core.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchUserResponse(
    @field:Json(name = "total_count") val total_count: Int,
    @field:Json(name = "incomplete_results") val incomplete_results: Boolean,
    @field:Json(name = "items") val items: List<User>
)
