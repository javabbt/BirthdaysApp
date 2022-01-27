package com.example.birthdaysapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.birthdaysapp.R
import com.example.birthdaysapp.data.models.Birthday
import com.example.birthdaysapp.databinding.DetailsFragmentBinding
import com.example.birthdaysapp.databinding.HomeFragmentBinding

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private val viewModel:DetailsViewModel by viewModels()
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val birthday:Birthday = arguments?.getSerializable("birthday") as Birthday
        binding.apply {
            profile.avatarInitials = birthday.name.first[0]+""
            name.text = birthday.name.first
            age.text = String.format(getString(R.string.age),birthday.dob.age)
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


}