package com.example.core.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    var page: Int = 0,
    @field:Json(name = "login")
    val name: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "avatar_url") val avatarUrl: String
) : Parcelable {

}
