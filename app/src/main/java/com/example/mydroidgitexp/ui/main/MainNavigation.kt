package com.example.mydroidgitexp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment

interface NavigationHost {
    // register ui navigation here
}

open class MainNavigationFragment : Fragment(){

    protected var navigationHost: NavigationHost? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHost) {
            navigationHost = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
    }

}