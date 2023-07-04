package com.example.core.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserInfo(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "login") val login: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "company") val company: String?,
    @field:Json(name = "blog") val blog: String,
    @field:Json(name = "location") val location: String?,
    @field:Json(name = "public_repos") val public_repos: Int,
    @field:Json(name = "public_gists") val public_gists: Int,
    @field:Json(name = "followers") val followers: Int,
    @field:Json(name = "following") val following: Int,
    @field:Json(name = "created_at") val created_at: String,
) : Parcelable
