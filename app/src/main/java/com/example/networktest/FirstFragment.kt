package com.example.networktest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.networktest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val catViewModel: CatViewModel by viewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        binding.submitButton.setOnClickListener {
            catViewModel.networkCall()
        }

        catViewModel.result.observe(viewLifecycleOwner){
            setCatText(it)
        }

        catViewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.networkLoading.visibility = View.VISIBLE
            } else {
                binding.networkLoading.visibility = View.GONE
            }
        }

    }

    private fun setCatText(it: CatData){
        binding.textviewFact.text = it.fact
        binding.textviewFactLength.text = "Fact ${it.length.toString()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}