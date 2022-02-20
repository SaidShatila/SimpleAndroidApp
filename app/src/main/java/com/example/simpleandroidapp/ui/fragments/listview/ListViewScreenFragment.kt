package com.example.simpleandroidapp.ui.fragments.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.simpleandroidapp.data.remote.response.Todos
import com.example.simpleandroidapp.databinding.FragmentListViewScreenBinding
import com.example.simpleandroidapp.ui.adapters.TodosRecyclerViewAdapter
import com.example.simpleandroidapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewScreenFragment : Fragment() {

    private lateinit var binding: FragmentListViewScreenBinding
    private lateinit var todosRecyclerViewAdapter: TodosRecyclerViewAdapter
    private val listViewScreenViewModel by viewModels<ListViewScreenViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListViewScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()

    }

    private fun setUpObserver() {
        startRefreshing()
        getTodos()
        listViewScreenViewModel.getTodosList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    stopRefreshing()
                    response.data?.let {
                        setUpRecycler(it)
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), response?.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }

    private fun getTodos() {
        listViewScreenViewModel.fetchTodos()
    }

    private fun setUpRecycler(list: List<Todos>?) {
        val todosList = arrayListOf(
            Todos(1, 1, "Zico", false),
            Todos(2, 2, "Gringo", true)
        )
        with(binding) {
            todosRecyclerViewAdapter = TodosRecyclerViewAdapter()
            rvList.adapter = todosRecyclerViewAdapter
            list?.let {
                todosRecyclerViewAdapter.setUpData(it)
            }
        }
    }

    private fun startRefreshing() {
        binding.swipeRefresh.isRefreshing = true
    }

    private fun stopRefreshing() {
        binding.swipeRefresh.isRefreshing = false
    }
}