package com.example.mydroidgitexp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.mydroidgitexp.R
import com.example.mydroidgitexp.adapters.UserAdapter
import com.example.mydroidgitexp.databinding.FragmentSearchBinding
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            adapter = UserAdapter()
            vm = viewModel
        }
        binding.toolbar.run {
            // TODO: inflate search menu

        }
    }

    private fun openUsers() {
        // TODO: navigate to UsersFragment
        //findNavController().navigate(SearchFragmentDirections.toUsers())
    }
}
