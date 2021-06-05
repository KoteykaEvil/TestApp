package com.onix.internship.survay.ui.tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentStubBinding
import com.onix.internship.survay.databinding.FragmentTestListBinding
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import com.onix.internship.survay.ui.stub.StubViewModel
import com.onix.internship.survay.ui.stub.StubViewModelFactory

class TestListFragment : Fragment() {
    private lateinit var binding: FragmentTestListBinding
    private val viewModel: TestListViewModel by viewModels{
        TestListViewModelFactory(
            SurvayDatabase.getInstance(requireContext()),
            SharedPrefs(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}