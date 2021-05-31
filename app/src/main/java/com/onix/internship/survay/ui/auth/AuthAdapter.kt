package com.onix.internship.survay.ui.auth

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onix.internship.survay.ui.auth.login.LoginFragment
import com.onix.internship.survay.ui.auth.register.RegisterFragment

class AuthAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = TabFragmentsList.values().size

    override fun createFragment(position: Int): Fragment {
        return TabFragmentsList.values()[position].fragment
    }
}

enum class TabFragmentsList(val fragment: Fragment) {
    LoginFragment(LoginFragment()), RegisterFragment(RegisterFragment())
}