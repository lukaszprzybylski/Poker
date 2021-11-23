package com.example.myapplication.poker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.poker.ListViewModel
import com.example.myapplication.poker.MainRepository
import com.example.myapplication.poker.MyViewModelFactory
import com.example.myapplication.poker.network.RetrofitService
import com.example.myapplication.poker.databinding.FragmentFirstBinding

class PlanetsFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var customAdapter: ListAdapter? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        binding.recycleView.layoutManager = mLayoutManager
        binding.recycleView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recycleView.itemAnimator = DefaultItemAnimator()
        binding.recycleView.adapter = customAdapter
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(ListViewModel::class.java)
        viewModel.allPlanets.observe(viewLifecycleOwner, {
            customAdapter = context?.let { _ -> ListAdapter(it.items) }
            binding.recycleView.adapter = customAdapter
        })

        viewModel.getAllPlanets()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}