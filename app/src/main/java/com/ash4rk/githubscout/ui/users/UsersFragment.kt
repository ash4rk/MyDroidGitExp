package com.ash4rk.githubscout.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ash4rk.core.model.User
import com.ash4rk.githubscout.R
import com.ash4rk.githubscout.adapters.OnUserClickListener
import com.ash4rk.githubscout.adapters.UserAdapter
import com.ash4rk.githubscout.databinding.FragmentUsersBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UsersFragment : BindingFragment<FragmentUsersBinding>(R.layout.fragment_users),
    OnUserClickListener {

    private val viewModel: UsersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            adapter = UserAdapter(this@UsersFragment)
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

    private fun openSearch() {
        findNavController().navigate(UsersFragmentDirections.toSearch())
    }

    override fun openUserInfo(user: User) {
        val action = UsersFragmentDirections.actionNavigationUsersToUserInfoFragment(user)
        findNavController().navigate(action)
    }
}
