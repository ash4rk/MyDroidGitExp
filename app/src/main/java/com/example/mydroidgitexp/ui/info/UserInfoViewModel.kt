package com.example.mydroidgitexp.ui.info

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.InfoRepository
import com.example.core.model.UserInfo
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class UserInfoViewModel @AssistedInject constructor(
    private val infoRepository: InfoRepository,
    @Assisted private val userName: String
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val userInfoFlow: Flow<UserInfo?> = infoRepository.fetchUserInfo(
        name = userName,
        onComplete = { isLoading = false },
        onError = {
            toastMessage = it
            Timber.d(it)
        }
    )

    @get:Bindable
    val userInfo: UserInfo? by userInfoFlow.asBindingProperty(viewModelScope, null)

    init {
        Timber.d("Init SearchViewModel")
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(userName: String): UserInfoViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            userName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userName) as T
            }
        }
    }
}