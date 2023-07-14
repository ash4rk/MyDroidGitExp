package com.ash4rk.githubscout.ui.main

import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BindingViewModel() {

    init {
        Timber.d("init MainViewModel")
    }
}