package com.example.mydroidgitexp.ui.info

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.InfoRepository
import com.example.core.model.UserInfo
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val infoRepository: InfoRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val userInfoFlow: Flow<UserInfo?> = infoRepository.fetchUserInfo(
        name = "ash4rk",
        onComplete = { isLoading = false },
        onError = {
            toastMessage = it
            Timber.d(it)
        }
    )


    @get:Bindable
    val userList: UserInfo? by userInfoFlow.asBindingProperty(viewModelScope, null)

    init {
        Timber.d("Init SearchViewModel")
    }

}