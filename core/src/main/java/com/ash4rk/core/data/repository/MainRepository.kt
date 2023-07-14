package com.ash4rk.core.data.repository

import androidx.annotation.WorkerThread
import com.ash4rk.core.model.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    @WorkerThread
    fun fetchUserList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<User>>

    @WorkerThread
    fun fetchSearchUserList(
        page: Int,
        query: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<User>>
}