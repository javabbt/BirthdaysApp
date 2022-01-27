package com.example.birthdaysapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.birthdaysapp.R
import com.example.birthdaysapp.data.models.Birthday
import com.example.birthdaysapp.databinding.HomeFragmentBinding
import com.example.birthdaysapp.ui.adapters.BirthdaysAdapter
import com.example.birthdaysapp.utils.ApiStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var birthdayAdapter: BirthdaysAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        birthdayAdapter.setParent(this)
        viewModel.birthdaysLiveData.observe(viewLifecycleOwner) { result ->
            // Update the UI here
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    val data = result.data
                    binding.progressBar.isVisible = false
                    birthdayAdapter.birthdays = data!!.results
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.isVisible = false
                }
                ApiStatus.LOADING -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() = binding.rvTodos.apply {
        adapter = birthdayAdapter
        layoutManager = LinearLayoutManager(activity)
        val mDividerItemDecoration = DividerItemDecoration(
            context,
            (layoutManager as LinearLayoutManager).orientation
        )
        addItemDecoration(mDividerItemDecoration)
    }

    fun goToDetails(bd: Birthday) {
        val bundle = bundleOf("birthday" to bd)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment , bundle , navOptions { // Use the Kotlin DSL for building NavOptions
            anim {
                enter = android.R.animator.fade_in
                exit = android.R.animator.fade_out
            }
        })
    }

}