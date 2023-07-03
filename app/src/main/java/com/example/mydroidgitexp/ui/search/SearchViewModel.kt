package com.example.mydroidgitexp.ui.search

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.MainRepository
import com.example.core.model.User
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val textQuery: MutableStateFlow<String> = MutableStateFlow("")
    private val userFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val userListFlow =
        userFetchingIndex.flatMapLatest { page ->
        textQuery.flatMapLatest { query ->
            if (query.isBlank()) {
                flowOf(emptyList())
            } else {
                mainRepository.fetchSearchUserList(
                    query = query,
                    page = page,
                    onStart = { isLoading = true },
                    onComplete = { isLoading = false },
                    onError = {
                        toastMessage = it
                        Timber.d(it)
                    }
                )
            }
        }
    }

    @get:Bindable
    val userList: List<User> by userListFlow.asBindingProperty(viewModelScope, emptyList())

    init {
        Timber.d("Init SearchViewModel")
    }

    fun onSearchQueryChanged(query: String) {
        val newQuery = query.trim().takeIf { it.length >= 2 } ?: ""
        if (textQuery.value != newQuery) {
            textQuery.value = newQuery
            userFetchingIndex.value = 0
        }
    }

    @MainThread
    fun fetchNextUserList() {
        if (!isLoading) {
            userFetchingIndex.value++
        }
    }
}