package com.example.simpleandroidapp.ui.fragments.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simpleandroidapp.databinding.FragmentListViewScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListViewScreenFragment : Fragment() {

    private lateinit var binding: FragmentListViewScreenBinding

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
    }


}