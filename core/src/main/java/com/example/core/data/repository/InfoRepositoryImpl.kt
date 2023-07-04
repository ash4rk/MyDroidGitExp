package com.example.core.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.example.core.database.UserInfoDao
import com.example.core.database.entity.mapper.asDomain
import com.example.core.database.entity.mapper.asEntity
import com.example.core.network.Dispatcher
import com.example.core.network.GitHubAppDispatchers
import com.example.core.network.model.mapper.ErrorResponseMapper
import com.example.core.network.service.GitHubClient
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@VisibleForTesting
class InfoRepositoryImpl @Inject constructor(
    private val githubClient: GitHubClient,
    private val userInfoDao: UserInfoDao,
    @Dispatcher(GitHubAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : InfoRepository {

    @WorkerThread
    override fun fetchUserInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val userInfo = userInfoDao.getUserInfo(name)
        if (userInfo == null) {
            val response = githubClient.fetchUserInfo(name)
            response.suspendOnSuccess {
                userInfoDao.insertUserInfo(data.asEntity())
                emit(data)
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        } else {
            emit(userInfo.asDomain())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}