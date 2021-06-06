package com.onix.internship.survay.ui.tests

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentTestListBinding
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs


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
        //setHasOptionsMenu(true);
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_log_out){
            viewModel.onLogOutClick()
            //findNavController().popBackStack()
        }
        return true
    }


}