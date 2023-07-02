package com.example.mydroidgitexp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mydroidgitexp.R
import com.example.mydroidgitexp.adapters.UserAdapter
import com.example.mydroidgitexp.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Qualifier

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    //@get:VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            adapter = UserAdapter()
            vm = viewModel
        }

        binding.toolbar.run {
            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.search) {
                    Timber.d("Navigate to Search")
                    openSearch()
                    true
                } else {
                    false
                }
            }
        }
    }
}

private fun openSearch() {
    // TODO: navigate via NavController
    return
}