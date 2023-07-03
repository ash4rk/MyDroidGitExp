package com.example.mydroidgitexp.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mydroidgitexp.R
import com.example.mydroidgitexp.adapters.UserAdapter
import com.example.mydroidgitexp.databinding.FragmentUsersBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UsersFragment : BindingFragment<FragmentUsersBinding>(R.layout.fragment_users) {

    private val viewModel: UsersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun openSearch() {
        findNavController().navigate(UsersFragmentDirections.toSearch())
    }
}
