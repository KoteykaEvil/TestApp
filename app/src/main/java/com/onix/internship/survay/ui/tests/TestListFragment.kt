package com.onix.internship.survay.ui.tests

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onix.internship.survay.R
import com.onix.internship.survay.databinding.FragmentTestListBinding
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.local.tables.tests.Test
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import com.onix.internship.survay.ui.tests.adapter.TestAdapter
import kotlin.system.exitProcess


class TestListFragment : Fragment() {
    private lateinit var binding: FragmentTestListBinding
    private val viewModel: TestListViewModel by viewModels {
        TestListViewModelFactory(
            SurvayDatabase.getInstance(requireContext()),
            SharedPrefs(requireContext())
        )
    }

    private val testAdapter: TestAdapter by lazy {
        TestAdapter(object : TestAdapter.ItemClickListener {
            override fun onClick(model: Test) {
                Toast.makeText(requireContext(), model.name, Toast.LENGTH_SHORT).show()
            }
        })
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
        viewModel.getDbData()
        setupRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
        viewModel.dbData.observe(viewLifecycleOwner, {
            testAdapter.update(it)
        })
    }

    private fun setupRecyclerView() {

        binding.testsListRecycler.apply {
            adapter = testAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_log_out) {
            viewModel.onLogOutClick()
        }
        return true
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
        requireActivity().let {
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
        }.show()
    }

}