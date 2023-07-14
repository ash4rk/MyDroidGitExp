package com.ash4rk.core.data.repository

import androidx.annotation.WorkerThread
import com.ash4rk.core.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface InfoRepository {

    @WorkerThread
    fun fetchUserInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<UserInfo>

}