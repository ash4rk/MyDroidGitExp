package com.ash4rk.core.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.ash4rk.core.database.SearchUserDao
import com.ash4rk.core.database.UserDao
import com.ash4rk.core.database.entity.mapper.asDomain
import com.ash4rk.core.database.entity.mapper.asEntity
import com.ash4rk.core.database.entity.mapper.asSearchEntity
import com.ash4rk.core.model.User
import com.ash4rk.core.network.Dispatcher
import com.ash4rk.core.network.GitHubAppDispatchers
import com.ash4rk.core.network.service.GitHubClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class MainRepositoryImpl @Inject constructor(
    private val githubClient: GitHubClient,
    private val userDao: UserDao,
    private val searchUserDao: SearchUserDao,
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
                users = data
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

    @WorkerThread
    override fun fetchSearchUserList(
        page: Int,
        query: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<User>> = flow {
        if (query != SearchUserDao.last_query) {
            searchUserDao.clearSearchUserTable()
            SearchUserDao.last_query = query
        }
        var users = searchUserDao.getSearchUserList(query, page).asDomain()
        if (users.isEmpty()) {
            val response = githubClient.fetchSearchUserList(query = query, page = page)
            response.suspendOnSuccess {
                users = data.items
                users.forEach { user -> user.page = page }
                searchUserDao.insertSearchUserList(users.asSearchEntity())
                emit(searchUserDao.getAllSearchUserList(page).asDomain())
            }.onFailure { // handles the all error cases from the API request fails.
                onError(message())
            }
        } else {
            emit(searchUserDao.getAllSearchUserList(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}