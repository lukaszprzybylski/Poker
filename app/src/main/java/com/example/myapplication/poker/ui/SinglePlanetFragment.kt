package com.example.myapplication.poker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.poker.ListViewModel
import com.example.myapplication.poker.MainRepository
import com.example.myapplication.poker.MyViewModelFactory
import com.example.myapplication.poker.network.RetrofitService
import com.example.myapplication.poker.databinding.FragmentSecondBinding

class SinglePlanetFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var viewModel: ListViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uid = arguments?.getString("uid").toString()
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(ListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getPlanet(uid)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}