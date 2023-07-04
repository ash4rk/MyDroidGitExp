package com.example.mydroidgitexp.ui.info

import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mydroidgitexp.R
import com.example.mydroidgitexp.databinding.FragmentUserInfoBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UserInfoFragment : BindingFragment<FragmentUserInfoBinding>(R.layout.fragment_user_info) {

    @set:Inject
    internal lateinit var userInfoViewModelFactory: UserInfoViewModel.AssistedFactory

    private val args: UserInfoFragmentArgs by navArgs()

    @get:VisibleForTesting
    private val viewModel: UserInfoViewModel by viewModels {
        UserInfoViewModel.provideFactory(userInfoViewModelFactory, args.user.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            vm = viewModel
        }
        binding.toolbar.run {
            inflateMenu(R.menu.search_menu)
            setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.back) {
                    Timber.d("Navigate to Users")
                    backToUsers()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun backToUsers() {
        findNavController().popBackStack()
    }

}
