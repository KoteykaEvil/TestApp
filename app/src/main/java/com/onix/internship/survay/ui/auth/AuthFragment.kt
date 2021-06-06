package com.onix.internship.survay.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentAuthBinding
import kotlin.system.exitProcess


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.authViewPager.adapter = AuthAdapter(this)
        binding.authViewPager.offscreenPageLimit = 2
        binding.apply {
            TabLayoutMediator(authTabLayout, authViewPager) { tab, position ->
                tab.text = requireContext().resources.getStringArray(R.array.tab_strings)[position]
            }.attach()
        }
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                showAreYouSureDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    private fun showAreYouSureDialog() {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage(R.string.exit_msg_title)
                setPositiveButton(
                    R.string.yes
                ) { _, _ ->
                    exitProcess(1)
                }
                setNegativeButton(
                    R.string.cancel
                ) { _, _ ->
                    // User cancelled the dialog
                }
            }
            builder.create()
        }?.show()
    }

}