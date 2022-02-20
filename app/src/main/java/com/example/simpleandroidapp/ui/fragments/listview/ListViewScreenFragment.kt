package com.example.simpleandroidapp.ui.fragments.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simpleandroidapp.data.remote.response.Todos
import com.example.simpleandroidapp.databinding.FragmentListViewScreenBinding
import com.example.simpleandroidapp.ui.adapters.TodosRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewScreenFragment : Fragment() {

    private lateinit var binding: FragmentListViewScreenBinding
    private lateinit var todosRecyclerViewAdapter: TodosRecyclerViewAdapter

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
        setUpRecycler()
    }


    private fun setUpRecycler() {
        val todosList = arrayListOf(
            Todos(1, 1, "Zico", false),
            Todos(2, 2, "Gringo", true)
        )
        with(binding) {
            todosRecyclerViewAdapter = TodosRecyclerViewAdapter()
            rvList.adapter = todosRecyclerViewAdapter
            todosRecyclerViewAdapter.setUpData(todosList)
        }
    }

    private fun startRefreshing() {
        binding.swipeRefresh.isRefreshing = true
    }

    private fun stopRefreshing() {
        binding.swipeRefresh.isRefreshing = false
    }
}