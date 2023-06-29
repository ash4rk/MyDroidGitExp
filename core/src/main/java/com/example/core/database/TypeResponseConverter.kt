package com.example.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.model.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromString(value: String): List<User>? {
        val listType =
            Types.newParameterizedType(List::class.java, User::class.java)
        val adapter: JsonAdapter<List<User>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<User>?): String {
        return "0"
    }
}
