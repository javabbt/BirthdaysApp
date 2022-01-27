package com.example.birthdaysapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.birthdaysapp.databinding.HomeFragmentBinding
import com.example.birthdaysapp.ui.adapters.BirthdaysAdapter
import com.example.birthdaysapp.utils.ApiResult
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
    @Inject lateinit var birthdayAdapter: BirthdaysAdapter

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
        viewModel.birthdaysLiveData.observe(viewLifecycleOwner) { result ->
            // Update the UI here
            when(result.status) {
                ApiStatus.SUCCESS ->  {
                    val data = result.data
                    binding.progressBar.isVisible = false
                    birthdayAdapter.birthdays = data!!
                }
                ApiStatus.ERROR ->   {
                    binding.progressBar.isVisible = false
                }
                ApiStatus.LOADING ->  {
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
    }

}