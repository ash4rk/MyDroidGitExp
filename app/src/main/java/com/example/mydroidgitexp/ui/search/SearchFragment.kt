package com.example.mydroidgitexp.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.model.User
import com.example.mydroidgitexp.R
import com.example.mydroidgitexp.adapters.OnUserClickListener
import com.example.mydroidgitexp.adapters.UserAdapter
import com.example.mydroidgitexp.databinding.FragmentSearchBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search),
    OnUserClickListener {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            adapter = UserAdapter(this@SearchFragment)
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

        binding.searchView.apply {
            setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    dismissKeyboard(this@apply)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.onSearchQueryChanged(newText)
                    return true
                }
            })

            // Set focus on the SearchView and open the keyboard
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    showKeyboard(view.findFocus())
                }
            }
            requestFocus()
        }
    }

    override fun onPause() {
        dismissKeyboard(binding.searchView)
        super.onPause()
    }

    private fun showKeyboard(view: View) {
        ViewCompat.getWindowInsetsController(view)?.show(WindowInsetsCompat.Type.ime())
    }

    private fun dismissKeyboard(view: View) {
        ViewCompat.getWindowInsetsController(view)?.hide(WindowInsetsCompat.Type.ime())
    }

    private fun backToUsers() {
        findNavController().popBackStack()
    }

    override fun openUserInfo(user: User) {
        val action = SearchFragmentDirections.actionNavigationSearchToUserInfoFragment(user)
        findNavController().navigate(action)
    }
}
