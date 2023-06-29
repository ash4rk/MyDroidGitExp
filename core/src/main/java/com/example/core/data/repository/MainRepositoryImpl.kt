package com.example.core.data.repository

import androidx.annotation.WorkerThread
import com.example.core.database.UserDao
import com.example.core.database.entity.mapper.asDomain
import com.example.core.database.entity.mapper.asEntity
import com.example.core.network.Dispatcher
import com.example.core.network.GitHubAppDispatchers
import com.example.core.network.service.GitHubClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val githubClient: GitHubClient,
    private val userDao: UserDao,
    @Dispatcher(GitHubAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : MainRepository {

    @WorkerThread
    override fun fetchUserList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var users = userDao.getUserList(page).asDomain()
        if (users.isEmpty()) {
            val response = githubClient.fetchUserList(page = page)
            response.suspendOnSuccess {
                users = data.results
                users.forEach { user -> user.page = page }
                userDao.insertUserList(users.asEntity())
                emit(userDao.getAllUserList(page).asDomain())
            }.onFailure { // handles the all error cases from the API request fails.
                onError(message())
            }
        } else {
            emit(userDao.getAllUserList(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}