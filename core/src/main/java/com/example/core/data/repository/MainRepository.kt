package com.example.core.data.repository

import androidx.annotation.WorkerThread
import com.example.core.model.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    @WorkerThread
    fun fetchUserList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<User>>
}